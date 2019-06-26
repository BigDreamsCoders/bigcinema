package com.uca.bigdreamscoders.bigcinema.services

import com.uca.bigdreamscoders.bigcinema.repositories.ProvinceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProvinceService{
    @Autowired
    lateinit var provinceRepository: ProvinceRepository

    fun findAll() = provinceRepository.findAll()
    fun findByProId(proId:String) = provinceRepository.findByProId(proId)
}