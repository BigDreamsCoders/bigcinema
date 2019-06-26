package com.uca.bigdreamscoders.bigcinema.form

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

data class RegisterForm(
        @field:NotEmpty(message="*Missing given name")
        @field:Pattern(regexp = "^([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\']+[\\s])+([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\'])+[\\s]?([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\'])?\$",
                message = "*Invalid format")
        var name : String="",

        @field:NotEmpty(message="*Missing lastname")
                @field:Pattern(regexp = "^([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\']+[\\s])+([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\'])+[\\s]?([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\'])?\$",
                message = "*Invalid format")
        var lastName : String="",
        @field:NotEmpty(message="*Please provide a username")
        @field:Pattern(regexp = "^[a-zA-Z0-9]+",
                message = "*Invalid format." +
                        " Please enter a password only using [a-zA-Z0-9]")
        var username : String="",
        @field:NotEmpty(message="*Please provide the password")
        @field:Pattern(regexp = "^[a-zA-Z0-9]+", message = "*Invalid format. " +
                " Please enter a password only using [a-zA-Z0-9]")
        var password : String="",

        @field:NotEmpty(message="*Please provide a date of birth")
        var dateBirth : String="",
        @field:NotEmpty(message="*Error no ID")
        @field:NotNull(message="*Somehow is null")
        var proId : String="",

        @field:NotEmpty(message="*Please provide an address")
        var address : String=""
)
{

}