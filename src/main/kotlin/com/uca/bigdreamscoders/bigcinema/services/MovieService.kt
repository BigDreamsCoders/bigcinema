package com.uca.bigdreamscoders.bigcinema.services

import com.uca.bigdreamscoders.bigcinema.repositories.MovieRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MovieService{
    @Autowired
    lateinit var movieRepository: MovieRepository

    fun findAll() = movieRepository.findAll()
}