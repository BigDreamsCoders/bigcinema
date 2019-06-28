package com.uca.bigdreamscoders.bigcinema.controller

import com.uca.bigdreamscoders.bigcinema.domain.Reservation
import com.uca.bigdreamscoders.bigcinema.form.ReservationForm
import com.uca.bigdreamscoders.bigcinema.form.ReviewForm
import com.uca.bigdreamscoders.bigcinema.services.*
import com.uca.bigdreamscoders.bigcinema.utils.GeneralUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import java.math.BigDecimal
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession
import java.text.SimpleDateFormat
import java.text.DateFormat
import javax.validation.Valid


@Controller
class ReservationController{
    @Autowired
    lateinit var reservationService: ReservationService
    @Autowired
    lateinit var movieService: MovieService
    @Autowired
    lateinit var listingService: ListingService
    @Autowired
    lateinit var accountService: AccountService
    @Autowired
    lateinit var recordService : RecordService

    @PostMapping("/movie/{movId}/reservation/new")
    fun reservationPrepare(@Valid reservationForm: ReservationForm, results:BindingResult,
                           @PathVariable("movId") movId : String,
                           request: HttpServletRequest, model: Model, pageable: Pageable):String{
        val accountM = GeneralUtils.returnAccount(request, accountService)
        if(accountM != null){
            model.addAttribute("money", accountM.accBalance)
        }
        if(results.hasErrors()) {
            movieService.findByOne(movId).ifPresent{
                model.addAttribute("movie", it)
                val lists = listingService.findByActStatusAndMovieMovId(true, it.movId,
                        pageable).toList()
                model.addAttribute("listing", lists)
            }
            model.addAttribute("reservationForm", reservationForm)
            return "view-listing"
        }
        val finalForm = ReviewForm()
        val reservation = Reservation()
        val dateFormat = SimpleDateFormat("yyyy/MM/dd")
        val date = Date()

        val accountLoged: String? = request.session.getAttribute("USER_LOGED") as String?
        var accId = accountLoged!!.split(Regex("[=]"),3)[2]
        accId = accId.split(Regex("[,]"))[0].trim()

        val account = accountService.findByOne(accId)
        val listing = listingService.findByOne(reservationForm.referenceId)
        val movie = movieService.findByOne(movId)

        account.ifPresent {
            if(it.accBalance.subtract(reservationForm.creditUse)< BigDecimal.ZERO){
                model.addAttribute("error", "Not enough credits in your account")
            }
            finalForm.accountId = it.accId
            finalForm.usedBalance = BigDecimal.valueOf(reservationForm.creditUse.toLong()+0.00).setScale(2)
            reservation.account = it
            reservation.usedBalance = BigDecimal.valueOf(reservationForm.creditUse.toLong()+0.00).setScale(2)
        }

        listing.ifPresent {
            if(it.avaiSeats-reservationForm.resSeats<0){
                model.addAttribute("error", "Not enough seats for the request")
            }
            finalForm.listingId = it.lisId
            finalForm.requestedSeats = reservationForm.resSeats
            reservation.listing = it
            reservation.requestedSeats=reservationForm.resSeats
        }

        if(reservationForm.resSeats>15){
            model.addAttribute("error", "The maximum amount of seats per purchase is 15")
        }

        when (model.containsAttribute("error")){
            true ->{
                movie.ifPresent{
                    model.addAttribute("movie", it)
                    val lists = listingService.findByActStatusAndMovieMovId(true, it.movId,
                            pageable).toList()
                    model.addAttribute("listing", lists)
                }
                model.addAttribute("reservationForm", reservationForm)
                return "view-listing"
            }
            else ->{
                finalForm.dateReserved = dateFormat.format(date)
                finalForm.grandTotal =  reservation.listing!!.entryFee!!.multiply(BigDecimal.valueOf(reservationForm.resSeats.toLong())).setScale(2)
                reservation.dateReserved = dateFormat.format(date)
                reservation.grandTotal = reservation.listing!!.entryFee!!.multiply(BigDecimal.valueOf(reservationForm.resSeats.toLong())).setScale(2)
                model.addAttribute("receipt", reservation)
                model.addAttribute("finalForm", finalForm)

                return "summary-reservation"
            }
        }
    }

    @PostMapping("reservation/create")
    fun reservationCreate(bought: ReviewForm, request: HttpServletRequest, model: Model, pageable: Pageable): String {
        val account = GeneralUtils.returnAccount(request, accountService)
        if(account != null){
            model.addAttribute("money", account.accBalance)
        }
        val reservation = Reservation()
        val lisObj = listingService.findByOne(bought.listingId)
        val accObj = accountService.findByOne(bought.accountId)
        if(lisObj.isPresent and accObj.isPresent){

            reservation.listing = lisObj.get()
            reservation.account = accObj.get()
            reservation.dateReserved = bought.dateReserved
            reservation.requestedSeats = bought.requestedSeats
            reservation.grandTotal = bought.grandTotal
            reservation.usedBalance = bought.usedBalance

            val returned = reservationService.save(reservation)

            lisObj.get().avaiSeats -= bought.requestedSeats
            lisObj.get().reserSeats += bought.requestedSeats
            listingService.save(lisObj.get())

            accObj.get().accBalance -= bought.usedBalance
            accountService.save(accObj.get())

            reservationService.save(reservation)

            recordService.updateRecord(returned.resId,"CREATE RECORD",true,account!!)

            return "redirect:/movie/${lisObj.get().movie!!.movId}/listing"

        }
        model.addAttribute("receipt", bought)
        return "summary-reservation"
    }

    @GetMapping("reservation/see")
    fun reservationSee(request: HttpServletRequest, model: Model, pageable: Pageable): String{
        val account = GeneralUtils.returnAccount(request, accountService)
        if(account != null){
            model.addAttribute("money", account.accBalance)
            model.addAttribute("records" , reservationService.findPersonal(account.accId, pageable).toList())
            return "view-transactions"
        }
        return "redirect:/dashboard-client"
    }

}