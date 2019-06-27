package com.uca.bigdreamscoders.bigcinema.controller

import com.uca.bigdreamscoders.bigcinema.domain.Movie
import com.uca.bigdreamscoders.bigcinema.form.ReasonForm
import com.uca.bigdreamscoders.bigcinema.services.MovieService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Controller
class MovieController{
    @Autowired
    lateinit var movieService: MovieService

    @PostMapping("/movie/active")
    fun active(reasonForm:ReasonForm,reason: String, model: Model): String {
        movieService.findByOne(reasonForm.Id).ifPresent {
            it.actStatus = !it.actStatus
            //TODO add a record
            movieService.save(it)
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
                             model: Model):String{
        model.addAttribute("movieForm", movie)
        if(result.hasErrors()){
            return "create-movie"
        }
        else{
            movieService.save(movie)
            model.addAttribute("movies", movieService.findAll().toList())
            model.addAttribute("reasonForm", ReasonForm())
            return "dashboard-movie"
        }
    }

    @RequestMapping("/movie/{movId}/listing")
    fun getListingMovie(@PathVariable("movId") movId: String, model: Model):String{
        movieService.findByOne(movId).ifPresent{
            model.addAttribute("movie", it)
        }
        if(!model.containsAttribute("movie")){
            return "redirect:/dashboard-client"
        }
        return "view-listing"
    }
}