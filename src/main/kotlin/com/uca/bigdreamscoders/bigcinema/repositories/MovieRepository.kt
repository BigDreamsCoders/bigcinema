package com.uca.bigdreamscoders.bigcinema.repositories

import com.uca.bigdreamscoders.bigcinema.domain.Movie
import org.springframework.data.repository.CrudRepository

interface MovieRepository : CrudRepository<Movie, Int>{

}