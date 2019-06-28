package com.uca.bigdreamscoders.bigcinema.repositories

import com.uca.bigdreamscoders.bigcinema.domain.Province
import org.springframework.data.repository.CrudRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface ProvinceRepository : CrudRepository<Province, Int>{
    fun findByStateStaCor(staId: Int?, pageable: Pageable): Page<Province>
    fun findByProCorAndStateStaCor(proId: Int?, staId: Int?): Optional<Province>
    fun findByProId(proId : String) : Optional<Province>
}