package com.uca.bigdreamscoders.bigcinema.domain

import javax.persistence.*

@Entity(name = "state")
data class State (

        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "state_sta_cor_seq")
        @SequenceGenerator(sequenceName = "state_sta_cor_seq",  name = "state_sta_cor_seq")
        @Column (name = "sta_cor")
        var staCor : Int?=null,

        @Id
        @Column(name="sta_id")
        var staId : String = "",

        @Column(name="name")
        var staName : String ="",

        @OneToMany(fetch = FetchType.LAZY,
                mappedBy = "state")
        var provinces : Set<Province>?=null

)
{
        override fun toString(): String = "state = {staCor=$staCor, id = $staId , name = $staName}"
}