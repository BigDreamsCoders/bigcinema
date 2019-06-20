package com.uca.bigdreamscoders.bigcinema.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.math.BigDecimal
import javax.persistence.*

@Entity(name="listing")
data class Listing(
        @Id
        @GeneratedValue
        @Column(name = "lis_cor")
        var lisCor : Int,

        @Column(name="lis_id")
        var lisId : String = "",

        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name= "mov_id", nullable = false)
        @OnDelete(action = OnDeleteAction.CASCADE)
        @JsonIgnore
        var movie : Movie,

        @Column(name="active_status")
        var actStatus : Boolean,

        @Column(name="format_type")
        var formatType : String,

        @Column(name="start_time")
        var startTime: String,

        @Column(name = "available_seats")
        var avaiSeats : Int,

        @Column(name = "reserved_seats")
        var reserSeats : Int,

        @Column(name = "entry_fee")
        var entryFee : BigDecimal
)
{
    override fun toString(): String = "Listing{id=$lisId, movie = $movie, active = $actStatus," +
            "format = $formatType, start = $startTime, seats=$avaiSeats, reserved = $reserSeats," +
            "fee=$entryFee}"
}