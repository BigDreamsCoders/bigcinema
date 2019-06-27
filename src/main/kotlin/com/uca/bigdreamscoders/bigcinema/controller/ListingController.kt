package com.uca.bigdreamscoders.bigcinema.controller
import com.uca.bigdreamscoders.bigcinema.domain.Listing
import com.uca.bigdreamscoders.bigcinema.domain.Movie
import org.springframework.data.domain.Pageable
import com.uca.bigdreamscoders.bigcinema.form.ListingForm
import com.uca.bigdreamscoders.bigcinema.form.ReasonForm
import com.uca.bigdreamscoders.bigcinema.services.ListingService
import com.uca.bigdreamscoders.bigcinema.services.MovieService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.validation.Valid

@Controller
class ListingController{
    @Autowired
    lateinit var listingService: ListingService

    @Autowired
    lateinit var movieService: MovieService

    @GetMapping("/listing/prepare")
    fun prepareCreateListing(listingForm: ListingForm, model: Model, pageable: Pageable ): String{
        model.addAttribute("movies", movieService.findByActStatus(true, pageable).toList())
        model.addAttribute("listingForm", listingForm)
        return "create-listing"
    }

    @PostMapping("/listing/active")
    fun active(reasonForm: ReasonForm, reason: String, model: Model): String {
        listingService.findByOne(reasonForm.Id).ifPresent {
            it.actStatus = !it.actStatus
            //TODO add a record
            listingService.save(it)
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
                             model: Model):String{
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
            listingService.save(newListing)
            if(!model.containsAttribute("message")){
                model.addAttribute("error", "Error creating the new account")
            }
            model.addAttribute("listings", listingService.findAll().toList())
            model.addAttribute("listingForm", ListingForm())
            return "dashboard-listing"
        }
    }


}