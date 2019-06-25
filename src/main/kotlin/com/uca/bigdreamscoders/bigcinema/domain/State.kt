package com.uca.bigdreamscoders.bigcinema.domain

import javax.persistence.*

@Entity(name = "state")
data class State (
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "state_sta_cor_seq")
        @SequenceGenerator(sequenceName = "state_sta_cor_seq",  name = "state_sta_cor_seq")
        @Column (name = "sta_cor")
        var staCor : Int?=null,

        @Column(name="sta_id")
        var staId : String = "",

        @Column(name="name")
        var staName : String ="",

        @OneToMany(fetch = FetchType.EAGER,
                mappedBy = "state")
        var entradas : Set<Province>?=null

)
{
    override fun toString(): String = "State{cor=$staCor, id = $staId , name = $staName}"
}