package com.uca.bigdreamscoders.bigcinema.services

import com.uca.bigdreamscoders.bigcinema.domain.Listing
import com.uca.bigdreamscoders.bigcinema.repositories.ListingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ListingService{
    @Autowired
    lateinit var listingRepository: ListingRepository

    fun findAll() = listingRepository.findAll()

    fun findByOne(lisId:String) = listingRepository.findByLisId(lisId)

    fun save (listing:Listing) = listingRepository.save(listing)
}