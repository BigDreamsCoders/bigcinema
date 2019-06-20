package com.uca.bigdreamscoders.bigcinema.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity(name = "account")
data class Account (
        @Id
        @GeneratedValue
        @Column(name = "acc_cor")
        var accCor : Int,

        @Column(name="acc_id")
        var accId : String = "",

        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name= "pro_id", nullable = false)
        @OnDelete(action = OnDeleteAction.CASCADE)
        @JsonIgnore
        var province: Province,

        @Column(name="name")
        var name : String ="",

        @Column(name="lastname")
        var lastname : String ="",

        @Column(name="username")
        var username : String ="",

        @Column(name="password")
        var password : String ="",

        @Column(name="account_role")
        var accRole : Int,

        @Column(name="active_account")
        var accActive : Boolean,

        @Column(name="activeSession")
        var actSession : Boolean,

        @Column(name="inactivation_reason")
        var inacReason : String = "",

        @Column(name="date_of_birth")
        var dateBirth : Date,

        @Column(name="age")
        var age : Int,

        @Column(name="account_balance")
        var accBalance : BigDecimal,

        @Column(name="address")
        var address : String = ""

)
{
    override fun toString(): String = "Account{id = $accId , name = $name, lastname= $lastname" +
            "province = $province, role = $accRole, accActive = $accActive, loged = $actSession" +
            "reason = $inacReason, dateBirth = $dateBirth, age = $age, balance = $accBalance," +
            "address = $address }"
}