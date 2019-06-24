package com.uca.bigdreamscoders.bigcinema.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity(name = "reservation")
data class Reservation(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_res_cor_seq")
        @SequenceGenerator(sequenceName = "reservation_res_cor_seq",  name = "reservation_res_cor_seq")
        @Column(name = "res_cor")
        var resCor : Int?=null,

        @Column(name = "res_id")
        var resId : String ="",

        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name= "acc_id", nullable = false)
        @OnDelete(action = OnDeleteAction.CASCADE)
        var account : Account,

        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name= "lis_id", nullable = false)
        @OnDelete(action = OnDeleteAction.CASCADE)
        var listing : Listing,

        @Column(name = "requested_seats")
        var requestedSeats : Int?=null,

        @Column(name = "balance_used")
        var usedBalance : BigDecimal?=null,

        @Column(name = "grand_total")
        var grandTotal : BigDecimal?= null,

        @Column(name = "date_reserved")
        var dateReserved : String = ""
)
{
    override fun toString(): String = "Reservation{cor=$resCor, id = $resId , account = $account, listing = $listing" +
            "requestedSeats= $requestedSeats, usedBalance=$usedBalance, usedBalance =$usedBalance," +
            " grandTotal = $grandTotal, dateReserved = $dateReserved }"
}