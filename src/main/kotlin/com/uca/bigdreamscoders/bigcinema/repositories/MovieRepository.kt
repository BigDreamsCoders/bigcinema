package com.uca.bigdreamscoders.bigcinema.repositories

import com.uca.bigdreamscoders.bigcinema.domain.Movie
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import java.util.*

interface MovieRepository : CrudRepository<Movie, Int>{
    fun findByActStatus(actStatus : Boolean , pageable: Pageable): Page<Movie>
    fun findByMovId(movId: String) : Optional<Movie>
}