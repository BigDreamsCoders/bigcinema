package com.uca.bigdreamscoders.bigcinema.controller

import com.uca.bigdreamscoders.bigcinema.services.ReservationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller
class ReservationController{
    @Autowired
    lateinit var reservationService: ReservationService

    
}