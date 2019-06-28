package com.uca.bigdreamscoders.bigcinema.controller

import com.uca.bigdreamscoders.bigcinema.form.ReasonForm
import com.uca.bigdreamscoders.bigcinema.repositories.AccountRepository
import com.uca.bigdreamscoders.bigcinema.services.*
import com.uca.bigdreamscoders.bigcinema.utils.GeneralUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

@Controller
class MainController{

    @Autowired
    lateinit var accountService: AccountService
    @Autowired
    lateinit var movieService: MovieService
    @Autowired
    lateinit var listingService: ListingService
    @Autowired
    lateinit var recordService: RecordService
    @Autowired
    lateinit var reservationService : ReservationService


    @RequestMapping("/dashboard-account", method = [RequestMethod.POST, RequestMethod.GET])
    fun dashboardAccount(request: HttpServletRequest, model: Model): String{
        model.addAttribute("accounts", accountService.findAll().toList())
        model.addAttribute("reasonForm", ReasonForm())
        return "dashboard-account"
    }

    @RequestMapping("/dashboard-movie", method = [RequestMethod.POST, RequestMethod.GET])
    fun dashboardMovie(request: HttpServletRequest,model: Model): String{
        model.addAttribute("movies", movieService.findAll().toList())
        model.addAttribute("reasonForm", ReasonForm())
        return "dashboard-movie"
    }

    @RequestMapping("/dashboard-listing", method = [RequestMethod.POST, RequestMethod.GET])
    fun dashboardListing(request: HttpServletRequest, model: Model): String{
        model.addAttribute("listings", listingService.findAll().toList())
        model.addAttribute("reasonForm", ReasonForm())
        return "dashboard-listing"
    }

    @RequestMapping("/dashboard-client", method = [RequestMethod.POST, RequestMethod.GET])
    fun dashboardClient(request: HttpServletRequest, model: Model, pageable: Pageable): String{
        val account = GeneralUtils.returnAccount(request, accountService)
        if(account != null){
            model.addAttribute("money", account.accBalance)
        }
        model.addAttribute("movies", movieService.findByActStatus(true, pageable).toList())
        return "dashboard-client"
    }

    @RequestMapping("/dashboard-record", method = [RequestMethod.POST, RequestMethod.GET])
    fun dashboardRecord(request: HttpServletRequest, model: Model): String{
        model.addAttribute("records", recordService.findAll().toList())
        return "dashboard-record"
    }

    @RequestMapping("/dashboard-transaction", method = [RequestMethod.POST, RequestMethod.GET])
    fun reservationSee(request: HttpServletRequest, model: Model, pageable: Pageable): String{
        model.addAttribute("records" , reservationService.findAll().toList())
        return "dashboard-transaction"
    }

    @RequestMapping("/logout", method = [RequestMethod.POST, RequestMethod.GET])
    fun logOut(request: HttpServletRequest ):String{
        val account = GeneralUtils.returnAccount(request, accountService)
        if(account != null){
            account.actSession = false
            accountService.save(account)
            request.session.invalidate()
        }
        return "redirect:/"
    }

}