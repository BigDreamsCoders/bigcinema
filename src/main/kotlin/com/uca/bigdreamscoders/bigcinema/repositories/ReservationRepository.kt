package com.uca.bigdreamscoders.bigcinema.repositories

import com.uca.bigdreamscoders.bigcinema.domain.Reservation
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import java.util.*

interface ReservationRepository : CrudRepository<Reservation, Int>{
    fun findByProId(accId: Int?, pageable: Pageable): Page<Reservation>
    fun findByIdAndAccId(resId: Int?, accId: Int?): Optional<Reservation>
    fun findByIdAndLisId(resId: Int?, lisId: Int?): Optional<Reservation>
}