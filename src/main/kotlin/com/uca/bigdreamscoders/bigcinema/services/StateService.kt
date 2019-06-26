package com.uca.bigdreamscoders.bigcinema.services

import com.uca.bigdreamscoders.bigcinema.repositories.StateRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StateService{
    @Autowired
    lateinit var stateRepository: StateRepository

    fun findAll() = stateRepository.findAll()
}