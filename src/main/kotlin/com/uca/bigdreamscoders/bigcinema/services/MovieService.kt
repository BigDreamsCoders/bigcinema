package com.uca.bigdreamscoders.bigcinema.services

import com.uca.bigdreamscoders.bigcinema.domain.Movie
import com.uca.bigdreamscoders.bigcinema.repositories.MovieRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.data.domain.Pageable

@Service
class MovieService{
    @Autowired
    lateinit var movieRepository: MovieRepository

    fun findAll() = movieRepository.findAll()

    fun findByActStatus(actStatus: Boolean, pageable: Pageable) = movieRepository.findByActStatus(actStatus, pageable)

    fun findByOne (movId: String) = movieRepository.findByMovId(movId)

    fun save(movie : Movie) = movieRepository.save(movie)
}