package com.uca.bigdreamscoders.bigcinema.repositories

import com.uca.bigdreamscoders.bigcinema.domain.State
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface StateRepository : CrudRepository<State, Int>{

}