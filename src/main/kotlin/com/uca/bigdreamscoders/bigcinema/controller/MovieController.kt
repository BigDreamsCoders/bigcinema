package com.uca.bigdreamscoders.bigcinema.controller

import com.uca.bigdreamscoders.bigcinema.domain.Movie
import com.uca.bigdreamscoders.bigcinema.form.ReasonForm
import com.uca.bigdreamscoders.bigcinema.services.AccountService
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
class MovieController{
    @Autowired
    lateinit var movieService: MovieService
    @Autowired
    lateinit var accountService : AccountService
    @Autowired
    lateinit var recordService : RecordService

    @PostMapping("/movie/active")
    fun active(reasonForm:ReasonForm,reason: String, model: Model, request : HttpServletRequest): String {
        val who = GeneralUtils.returnAccount(request, accountService)
        movieService.findByOne(reasonForm.Id).ifPresent {
            it.actStatus = !it.actStatus
            val returned = movieService.save(it)
            recordService.newRecord(returned.movId,"UPDATE MOVIE",it.actStatus,who!!)
            model.addAttribute("message", "Account modified")
        }
        if (!model.containsAttribute("message")) {
            model.addAttribute("error", "Problem trying to update")
        }
        model.addAttribute("movies", movieService.findAll().toList())
        model.addAttribute("reasonForm", ReasonForm())
        return "dashboard-movie"
    }

    @GetMapping("/movie/prepare")
    fun prepareCreateMovie(movie: Movie, model: Model): String{
        model.addAttribute("movieForm", movie)
        return "create-movie"
    }

    @PostMapping("/movie/create")
    fun accountCreateAccount(@Valid movie: Movie, result: BindingResult,
                             model: Model, request: HttpServletRequest):String{
        val who = GeneralUtils.returnAccount(request, accountService)
        model.addAttribute("movieForm", movie)
        if(result.hasErrors()){
            return "create-movie"
        }
        else{
            val returned = movieService.save(movie)
            recordService.newRecord(returned.movId,"CREATE MOVIE",false,who!!)
            model.addAttribute("movies", movieService.findAll().toList())
            model.addAttribute("reasonForm", ReasonForm())
            return "dashboard-movie"
        }
    }

}