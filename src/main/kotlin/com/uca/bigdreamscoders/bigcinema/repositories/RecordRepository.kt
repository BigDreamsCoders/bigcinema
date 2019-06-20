package com.uca.bigdreamscoders.bigcinema.repositories

import com.uca.bigdreamscoders.bigcinema.domain.Record
import org.springframework.data.repository.CrudRepository

interface RecordRepository : CrudRepository<Record, Int>{

}