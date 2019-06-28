package com.uca.bigdreamscoders.bigcinema.repositories

import com.uca.bigdreamscoders.bigcinema.domain.Reservation
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import java.util.*

interface ReservationRepository : CrudRepository<Reservation, Int>{
    fun findByAccountAccCor(accId: Int?, pageable: Pageable): Page<Reservation>
    fun findByListingLisCor(accId: Int?, pageable: Pageable): Page<Reservation>
    fun findByResCorAndAccountAccCor(resId: Int?, accId: Int?): Optional<Reservation>
    fun findByResCorAndListingLisCor(resId: Int?, lisId: Int?): Optional<Reservation>
    fun findByAccountAccId(accId: String, pageable: Pageable) : Page<Reservation>
}