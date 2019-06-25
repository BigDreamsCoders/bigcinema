package com.uca.bigdreamscoders.bigcinema.repositories

import com.uca.bigdreamscoders.bigcinema.domain.Account
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import java.util.*

interface AccountRepository : CrudRepository<Account, Int>{
    fun findByProvinceProCor(proId: Int?, pageable: Pageable): Page<Account>
    fun findByAccCorAndProvinceProCor(accId: Int?, proId: Int?): Optional<Account>
    fun findByUsernameAndPassword(username: String, password: String): Optional<Account>
    fun findByUsername(username: String) : Optional<Account>
}