package com.uca.bigdreamscoders.bigcinema.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity(name = "reservation")
data class Reservation(

        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_res_cor_seq")
        @SequenceGenerator(sequenceName = "reservation_res_cor_seq",  name = "reservation_res_cor_seq")
        @Column(name = "res_cor",  insertable=false)
        var resCor : Int?=null,

        @Id
        @Column(name = "res_id")
        var resId : String ="",

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name= "acc_id")
        @OnDelete(action = OnDeleteAction.CASCADE)
        var account : Account?=null,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name= "lis_id")
        @OnDelete(action = OnDeleteAction.CASCADE)
        var listing : Listing?=null,

        @Column(name = "requested_seats")
        var requestedSeats : Int=0,

        @Column(name = "balance_used")
        var usedBalance : BigDecimal= BigDecimal.ZERO,

        @Column(name = "grand_total")
        var grandTotal : BigDecimal = BigDecimal.ZERO,

        @Column(name = "date_reserved")
        var dateReserved : String = ""
)
{
    override fun toString(): String = "Reservation{cor=$resCor, id = $resId , account = $account, listing = $listing" +
            "requestedSeats= $requestedSeats, usedBalance=$usedBalance, usedBalance =$usedBalance," +
            " grandTotal = $grandTotal, dateReserved = $dateReserved }"
}