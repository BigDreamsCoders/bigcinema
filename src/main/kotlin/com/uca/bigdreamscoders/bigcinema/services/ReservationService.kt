package com.uca.bigdreamscoders.bigcinema.services

import com.uca.bigdreamscoders.bigcinema.repositories.ReservationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Service
class ReservationService {
    @Autowired
    lateinit var reservationRepository: ReservationRepository

    @PostMapping("/reservation/new")
    fun check(){
        
    }
}