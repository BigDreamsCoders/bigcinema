package com.uca.bigdreamscoders.bigcinema.controller

import com.uca.bigdreamscoders.bigcinema.form.ReasonForm
import com.uca.bigdreamscoders.bigcinema.repositories.AccountRepository
import com.uca.bigdreamscoders.bigcinema.services.ListingService
import com.uca.bigdreamscoders.bigcinema.services.MovieService
import com.uca.bigdreamscoders.bigcinema.services.RecordService
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
    lateinit var accountService: AccountRepository
    @Autowired
    lateinit var movieService: MovieService
    @Autowired
    lateinit var listingService: ListingService
    @Autowired
    lateinit var recordService: RecordService


    @RequestMapping("/dashboard-account", method = [RequestMethod.POST, RequestMethod.GET])
    fun dashboardAccount(session : HttpSession, model: Model): String{
        model.addAttribute("accounts", accountService.findAll().toList())
        model.addAttribute("reasonForm", ReasonForm())
        return "dashboard-account"
    }

    @RequestMapping("/dashboard-movie", method = [RequestMethod.POST, RequestMethod.GET])
    fun dashboardMovie(session : HttpSession,model: Model): String{
        model.addAttribute("movies", movieService.findAll().toList())
        model.addAttribute("reasonForm", ReasonForm())
        return "dashboard-movie"
    }

    @RequestMapping("/dashboard-listing", method = [RequestMethod.POST, RequestMethod.GET])
    fun dashboardListing(session : HttpSession, model: Model): String{
        model.addAttribute("listings", listingService.findAll().toList())
        model.addAttribute("reasonForm", ReasonForm())
        return "dashboard-listing"
    }

    @RequestMapping("/dashboard-client", method = [RequestMethod.POST, RequestMethod.GET])
    fun dashboardClient(session : HttpSession, model: Model, pageable: Pageable): String{
        model.addAttribute("movies", movieService.findByActStatus(true, pageable).toList())
        return "dashboard-client"
    }

    @RequestMapping("/logout", method = [RequestMethod.POST, RequestMethod.GET])
    fun logOut(request: HttpServletRequest ):String{
        val accountLoged: String? = request.session.getAttribute("USER_LOGED") as String?
        var accId = accountLoged!!.split(Regex("[=]"),3)[2]
        accId = accId.split(Regex("[,]"))[0].trim()
        accountService.findByAccId(accId).ifPresent {
            it.actSession = false
            accountService.save(it)
        }
        request.session.invalidate()
        return "redirect:/"
    }

}