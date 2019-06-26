package com.uca.bigdreamscoders.bigcinema.controller

import com.uca.bigdreamscoders.bigcinema.form.ReasonForm
import com.uca.bigdreamscoders.bigcinema.repositories.AccountRepository
import com.uca.bigdreamscoders.bigcinema.services.ListingService
import com.uca.bigdreamscoders.bigcinema.services.MovieService
import com.uca.bigdreamscoders.bigcinema.services.RecordService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

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


    @RequestMapping("/dashboard", method = [RequestMethod.POST, RequestMethod.GET])
    fun dashboard(model: Model): String{
        model.addAttribute("accounts", accountService.findAll().toList())
        model.addAttribute("reasonForm", ReasonForm())
        return "dashboard"
    }
}