package com.uca.bigdreamscoders.bigcinema.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.stereotype.Controller
import org.springframework.http.HttpStatus
import org.springframework.ui.Model
import javax.servlet.RequestDispatcher
import javax.servlet.http.HttpServletRequest


@Controller
class PersonalErrorController : ErrorController{

    @RequestMapping("/error")
    fun handleError(request: HttpServletRequest, model : Model): String {
        val status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)

        if (status != null) {
            model.addAttribute("error", Integer.valueOf(status.toString()))
            return "error"
        }
        return "index"
    }

    override fun getErrorPath(): String {
        return "/error"
    }
}