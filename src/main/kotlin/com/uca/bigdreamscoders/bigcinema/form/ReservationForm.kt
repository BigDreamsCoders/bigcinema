package com.uca.bigdreamscoders.bigcinema.form

import java.math.BigDecimal
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

data class ReservationForm(
        @field:Min(value = 1, message = "*Only positives")
        var resSeats : Int ?= null,
        @field:NotNull(message = "*Check at least one show")
        var lisId : String = "",
        @field:Min(value = 0,message = "*Only positives")
        var creditUse : BigDecimal?= null
){

}