package com.uca.bigdreamscoders.bigcinema.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.math.BigDecimal
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

@Entity(name = "account")
data class Account (
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_acc_cor_seq")
        @SequenceGenerator(sequenceName = "account_acc_cor_seq",  name = "account_acc_cor_seq")
        @Column(name = "acc_cor")
        var accCor : Int?=null,

        @Column(name="acc_id")
        var accId : String = "",

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name= "pro_id")
        @OnDelete(action = OnDeleteAction.CASCADE)
        var province: Province?=null,

        @Column(name="name")
        var name : String ="",

        @Column(name="lastname")
        var lastname : String ="",

        @Column(name="username")
        @field:NotEmpty(message = "*Please fill the username")
        @field:Size(min = 8, message = "*Incorrect format. At least 8 characters")
        var username : String ="",

        @Column(name="password")
        @field:NotEmpty(message = "*Please fill the password")
        @field:Size(min = 8, message = "*Incorrect format. At least 8 characters")
        var password : String ="",

        @Column(name="account_role")
        var accRole : Int ?= 0,

        @Column(name="active_account")
        var accActive : Boolean=false,

        @Column(name="activeSession")
        var actSession : Boolean=false,

        @Column(name="inactivation_reason")
        var inacReason : String = "",

        @Column(name="date_of_birth")
        var dateBirth : String = "",

        @Column(name="age")
        var age : Int?= 0,

        @Column(name="account_balance")
        var accBalance : BigDecimal?= BigDecimal.valueOf(20.00),

        @Column(name="address")
        var address : String = "",

        @OneToMany(fetch = FetchType.LAZY,
                mappedBy = "account")
        var entradas : Set<Reservation>?=null
)
{
    override fun toString(): String = "Account{cor=$accCor, id = $accId , name = $name, lastname= $lastname" +
            "province = $province, role = $accRole, accActive = $accActive, loged = $actSession" +
            "reason = $inacReason, dateBirth = $dateBirth, age = $age, balance = $accBalance," +
            "address = $address }"
}