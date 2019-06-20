package com.uca.bigdreamscoders.bigcinema.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity(name = "state")
data class State (
        @Id
        @GeneratedValue
        @Column (name = "sta_cor")
        var staCor : Int,

        @Column(name="sta_id")
        var staId : String = "",

        @Column(name="name")
        var staName : String =""

)
{
    override fun toString(): String = "State{id = $staId , name = $staName}"
}