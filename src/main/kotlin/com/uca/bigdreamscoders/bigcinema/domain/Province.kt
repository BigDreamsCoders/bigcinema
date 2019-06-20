package com.uca.bigdreamscoders.bigcinema.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*

@Entity(name="province")
data class Province (
        @Id
        @GeneratedValue
        @Column(name = "pro_cor")
        var proCor : Int,

        @Column(name="pro_id")
        var proId : String = "",

        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name= "sta_id", nullable = false)
        @OnDelete(action = OnDeleteAction.CASCADE)
        @JsonIgnore
        var state :  State,

        @Column(name = "name")
        var proName : String = ""
)
{
    override fun toString(): String = "Province{id = $proId , name = $proName, state = $state}"
}