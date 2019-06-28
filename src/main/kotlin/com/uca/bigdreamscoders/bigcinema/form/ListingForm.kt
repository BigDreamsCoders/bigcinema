package com.uca.bigdreamscoders.bigcinema.form

import java.math.BigDecimal
import javax.validation.constraints.NotNull

data class ListingForm(
        var movId : String = "",
        var formatType :String="",
        var startTime : String="",
        var avaiSeats : Int = 0,
        var reserSeats : Int = 0,
        var entryFee : BigDecimal=BigDecimal(20.0)
){

}