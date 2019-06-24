package com.uca.bigdreamscoders.bigcinema.services

import com.uca.bigdreamscoders.bigcinema.repositories.ReservationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ReservationService {
    @Autowired
    lateinit var reservationRepository: ReservationRepository
}