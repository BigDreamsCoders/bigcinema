package com.uca.bigdreamscoders.bigcinema.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.uca.bigdreamscoders.bigcinema.form.AdminRegisterForm
import com.uca.bigdreamscoders.bigcinema.form.RegisterForm
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.math.BigDecimal
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity(name = "account")
data class Account (
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_acc_cor_seq")
        @SequenceGenerator(sequenceName = "account_acc_cor_seq",  name = "account_acc_cor_seq")
        @Column(name = "acc_cor",  insertable=false)
        var accCor : Int?=null,

        @Id
        @Column(name="acc_id")
        var accId : String = "",

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name= "pro_id")
        @OnDelete(action = OnDeleteAction.CASCADE)
        var province: Province?=null,

        @Column(name="name")
        var name : String ="",

        @Column(name="lastname")
        @field:NotEmpty(message = "*Please fill your lastname")
        var lastName : String ="",

        @Column(name="username")
        @field:NotEmpty(message = "*Please fill the username")
        @field:Size(min = 8, message = "*Incorrect format. At least 8 characters")
        var username : String ="",

        @Column(name="password")
        @field:NotEmpty(message = "*Please fill the password")
        @field:Size(min = 8, message = "*Incorrect format. At least 8 characters")
        var password : String ="",

        @Column(name="account_role")
        var accRole : Int ?= 2,

        @Column(name="active_account")
        var accActive : Boolean=false,

        @Column(name="activeSession")
        var actSession : Boolean=false,

        @Column(name="inactivation_reason")
        var inacReason : String = "",

        @Column(name="date_of_birth")
        @field:NotEmpty(message = "*Please add your birthday")
        var dateBirth : String = "",

        @Column(name="age")
        var age : Int?= 0,

        @Column(name="account_balance")
        var accBalance : BigDecimal = BigDecimal.valueOf(20.00),

        @Column(name="address")
        var address : String = "",

        @OneToMany(fetch = FetchType.LAZY,
                mappedBy = "account")
        var entries : Set<Reservation>?=null
)
{
        override fun toString(): String = "Account{cor=$accCor, accId = $accId , name = $name, lastName= $lastName ," +
        "$province, accRole = $accRole, accActive = $accActive, actSession = $actSession ," +
        "inacReason = $inacReason, dateBirth = $dateBirth, age = $age, accBalance = $accBalance," +
        "address = $address }"

        fun newAccount(registerForm: RegisterForm){
                password = registerForm.password
                dateBirth = registerForm.dateBirth
                lastName = registerForm.lastName
                name = registerForm.name
                username = registerForm.username
        }

        fun newAdminAccount(adminRegisterForm: AdminRegisterForm){
                password = adminRegisterForm.password
                dateBirth = adminRegisterForm.dateBirth
                lastName = adminRegisterForm.lastName
                name = adminRegisterForm.name
                username = adminRegisterForm.username
        }

        fun delegateAccActive() = if(accActive) "Active" else "Inactive"

        fun delegateRole() = when(accRole){
                1 -> "Admin"
                2 -> "Client"
                else -> "Client"
        }
}