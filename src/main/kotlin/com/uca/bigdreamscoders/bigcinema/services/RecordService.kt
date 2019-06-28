package com.uca.bigdreamscoders.bigcinema.services

import com.uca.bigdreamscoders.bigcinema.domain.Account
import com.uca.bigdreamscoders.bigcinema.domain.Record
import com.uca.bigdreamscoders.bigcinema.repositories.RecordRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*


@Service
class RecordService{
    @Autowired
    lateinit var recordRepository: RecordRepository

    fun findAll() = recordRepository.findAll()

    fun newRecord(id: String, description: String, actStatus: Boolean, account: Account) {
        val dateFormat = SimpleDateFormat("yyyy/MM/dd")
        val date = Date()
        val newRecord = Record()
        newRecord.recId = id
        newRecord.description = description
        newRecord.actStatus = actStatus
        newRecord.creationDate = dateFormat.format(date)
        newRecord.lastModification = dateFormat.format(date)
        newRecord.creatorAccount = account.accId
        newRecord.lastToModify = account.accId
        recordRepository.save(newRecord)
    }

    fun updateRecord(id: String, description: String, actStatus: Boolean, account: Account) {
        val dateFormat = SimpleDateFormat("yyyy/MM/dd")
        val date = Date()
        val newRecord = Record()
        newRecord.recId = id
        newRecord.description = description
        newRecord.actStatus = actStatus
        newRecord.lastModification = dateFormat.format(date)
        newRecord.lastToModify = account.accId
        recordRepository.save(newRecord)
    }
}