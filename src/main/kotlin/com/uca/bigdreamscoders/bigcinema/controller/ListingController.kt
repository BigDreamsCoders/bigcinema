package com.uca.bigdreamscoders.bigcinema.controller
import com.uca.bigdreamscoders.bigcinema.domain.Listing
import org.springframework.data.domain.Pageable
import com.uca.bigdreamscoders.bigcinema.form.ListingForm
import com.uca.bigdreamscoders.bigcinema.form.ReasonForm
import com.uca.bigdreamscoders.bigcinema.form.ReservationForm
import com.uca.bigdreamscoders.bigcinema.services.AccountService
import com.uca.bigdreamscoders.bigcinema.services.ListingService
import com.uca.bigdreamscoders.bigcinema.services.MovieService
import com.uca.bigdreamscoders.bigcinema.services.RecordService
import com.uca.bigdreamscoders.bigcinema.utils.GeneralUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@Controller
class ListingController{
    @Autowired
    lateinit var listingService: ListingService

    @Autowired
    lateinit var movieService: MovieService

    @Autowired
    lateinit var accountService : AccountService

    @Autowired
    lateinit var recordService: RecordService

    @GetMapping("/listing/prepare")
    fun prepareCreateListing(listingForm: ListingForm, model: Model, pageable: Pageable ): String{
        model.addAttribute("movies", movieService.findByActStatus(true, pageable).toList())
        model.addAttribute("listingForm", listingForm)
        return "create-listing"
    }

    @PostMapping("/listing/active")
    fun active(reasonForm: ReasonForm, reason: String, model: Model, request: HttpServletRequest): String {
        val who = GeneralUtils.returnAccount(request, accountService)
        listingService.findByOne(reasonForm.Id).ifPresent {
            it.actStatus = !it.actStatus

            val returned=listingService.save(it)
            recordService.updateRecord(returned.lisId,"UPDATE LISTING",it.actStatus,who!!)
            model.addAttribute("message", "Account modified")
        }
        if (!model.containsAttribute("message")) {
            model.addAttribute("error", "Problem trying to update")
        }
        model.addAttribute("listings", listingService.findAll().toList())
        model.addAttribute("reasonForm", ReasonForm())
        return "dashboard-listing"
    }

    @PostMapping("/listing/create")
    fun accountCreateAccount(@Valid listingForm: ListingForm, result: BindingResult,
                             model: Model, request: HttpServletRequest):String{
        val who = GeneralUtils.returnAccount(request, accountService)
        model.addAttribute("listingForm", listingForm)
        if(result.hasErrors()){
            return "create-listing"
        }
        else{
            val newListing = Listing()
            newListing.newListing(listingForm)
            movieService.findByOne(listingForm.movId).ifPresent{
                newListing.movie = it
                model.addAttribute("message", "Listing added")
            }
            val returned = listingService.save(newListing)
            recordService.newRecord(returned.lisId,"CREATE LISTING",false,who!!)
            if(!model.containsAttribute("message")){
                model.addAttribute("error", "Error creating the new account")
            }
            model.addAttribute("listings", listingService.findAll().toList())
            model.addAttribute("reasonForm", ReasonForm())
            return "dashboard-listing"
        }
    }
    @RequestMapping("/movie/{movId}/listing")
    fun getListingMovie(request: HttpServletRequest, @PathVariable("movId") movId: String, model: Model,
                        pageable:Pageable):String{
        val account = GeneralUtils.returnAccount(request, accountService)
        if(account != null){
            model.addAttribute("money", account.accBalance)
        }
        movieService.findByOne(movId).ifPresent{
            model.addAttribute("movie", it)
            val lists = listingService.findByActStatusAndMovieMovId(true, it.movId,
                    pageable).toList()
            model.addAttribute("listing", lists)
        }
        model.addAttribute("reservationForm", ReservationForm() )
        if(!model.containsAttribute("movie")){
            return "redirect:/dashboard-client"
        }
        return "view-listing"
    }

}