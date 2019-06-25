package com.uca.bigdreamscoders.bigcinema.form

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class LoginForm(
        @field:NotEmpty(message = "Please fill the username")
        @field:Size(min = 8, message = "Incorrect format. At least 8 characters")
        var username : String="",

        @field:NotEmpty(message = "Please fill the password")
        @field:Size(min = 8, message = "Incorrect format. At least 8 characters")
        var password : String= ""
){
}