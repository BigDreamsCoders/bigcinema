package com.uca.bigdreamscoders.bigcinema.repositories

import com.uca.bigdreamscoders.bigcinema.domain.Listing
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import java.util.*

interface ListingRepository : CrudRepository<Listing, Int>{
    fun findByMovieMovCor(movId: Int?, pageable: Pageable): Page<Listing>
    fun findByLisCorAndMovieMovCor(lisId: Int?, movId: Int?): Optional<Listing>
    fun findByLisId(lisId :String) : Optional<Listing>
}