package com.uca.bigdreamscoders.bigcinema.services

import com.uca.bigdreamscoders.bigcinema.domain.Account
import com.uca.bigdreamscoders.bigcinema.repositories.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


@Service
class AccountService  {
    @Autowired
    lateinit var accountRepository:AccountRepository

    fun findAll() = accountRepository.findAll()

    fun findByOne(accId:String) = accountRepository.findByAccId(accId)

    fun login(user: String, pass : String)= accountRepository.findByUsernameAndPassword(user, pass)

    fun checkUser(user:String) = accountRepository.findByUsername(user)

    fun save (account: Account) = accountRepository.save(account)

}