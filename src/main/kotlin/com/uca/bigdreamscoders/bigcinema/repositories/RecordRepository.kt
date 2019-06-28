package com.uca.bigdreamscoders.bigcinema.repositories

import com.uca.bigdreamscoders.bigcinema.domain.Account
import com.uca.bigdreamscoders.bigcinema.domain.Record
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.CrudRepository

interface RecordRepository : CrudRepository<Record, Int>{

}