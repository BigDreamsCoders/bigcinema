package com.uca.bigdreamscoders.bigcinema.form

import com.uca.bigdreamscoders.bigcinema.domain.Reservation
import java.math.BigDecimal

data class ReviewForm(
        var dateReserved : String="",
        var grandTotal : BigDecimal= BigDecimal.ZERO,
        var usedBalance : BigDecimal=BigDecimal.ZERO,
        var requestedSeats : Int=0,
        var listingId : String="",
        var accountId : String =""
){

}