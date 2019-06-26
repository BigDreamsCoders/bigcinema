package com.uca.bigdreamscoders.bigcinema.services

import com.uca.bigdreamscoders.bigcinema.repositories.RecordRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RecordService{
    @Autowired
    lateinit var recordRepository: RecordRepository

    fun findAll() = recordRepository.findAll()
}