package com.uca.bigdreamscoders.bigcinema.services

import com.uca.bigdreamscoders.bigcinema.domain.Reservation
import com.uca.bigdreamscoders.bigcinema.repositories.ReservationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Service
class ReservationService {
    @Autowired
    lateinit var reservationRepository: ReservationRepository

    fun save(reservation: Reservation) = reservationRepository.save(reservation)
}