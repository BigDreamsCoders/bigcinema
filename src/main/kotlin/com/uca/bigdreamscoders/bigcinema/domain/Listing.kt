package com.uca.bigdreamscoders.bigcinema.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.uca.bigdreamscoders.bigcinema.form.ListingForm
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.math.BigDecimal
import javax.persistence.*

@Entity(name="listing")
data class Listing(
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "listing_lis_cor_seq")
        @SequenceGenerator(sequenceName = "listing_lis_cor_seq",  name = "listing_lis_cor_seq")
        @Column(name = "lis_cor",  insertable=false)
        var lisCor : Int?=null,

        @Id
        @Column(name="lis_id")
        var lisId : String = "",

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name= "mov_id")
        @OnDelete(action = OnDeleteAction.CASCADE)
        var movie : Movie?=null,

        @Column(name="active_status")
        var actStatus : Boolean=false,

        @Column(name="format_type")
        var formatType : String="",

        @Column(name="start_time")
        var startTime: String= "",

        @Column(name = "available_seats")
        var avaiSeats : Int =0,

        @Column(name = "reserved_seats")
        var reserSeats : Int= 0,

        @Column(name = "entry_fee")
        var entryFee : BigDecimal?=null,

        @OneToMany(fetch = FetchType.LAZY,
                mappedBy = "listing")
        var entradas : Set<Reservation>?=null
)
{
    override fun toString(): String = "Listing{lisCor=$lisCor,lisId=$lisId, movie = $movie, active = $actStatus," +
            "format = $formatType, start = $startTime, seats=$avaiSeats, reserved = $reserSeats," +
            "fee=$entryFee}"

        fun delegateLisActive() = if (actStatus) "Active" else "Inactive"

        fun newListing(listingForm: ListingForm){
                avaiSeats = listingForm.avaiSeats
                reserSeats = listingForm.reserSeats
                entryFee = listingForm.entryFee
                formatType = listingForm.formatType
                startTime = listingForm.startTime
        }
}