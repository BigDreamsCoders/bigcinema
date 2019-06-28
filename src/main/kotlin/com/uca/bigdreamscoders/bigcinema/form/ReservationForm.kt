package com.uca.bigdreamscoders.bigcinema.form

import java.math.BigDecimal
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class ReservationForm(
        @field:Min(value = 1, message = "*The minimum number of seats is 1")
        @field:Max(value = 15, message = "*The maximum amount per reservation is 15")
        var resSeats : Int = 0,
        @field:NotNull(message = "*Check at least one")
        @field:NotEmpty(message = "*Check at least one")
        var referenceId : String = "",
        @field:Min(value = 0,message = "*The minimum of credit is 0")
        var creditUse : BigDecimal= BigDecimal(0)
){

}