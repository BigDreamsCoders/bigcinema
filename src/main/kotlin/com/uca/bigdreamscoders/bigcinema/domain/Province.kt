package com.uca.bigdreamscoders.bigcinema.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*

@Entity(name="province")
data class Province (

        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "province_pro_cor_seq")
        @SequenceGenerator(sequenceName = "province_pro_cor_seq",  name = "province_pro_cor_seq")
        @Column(name = "pro_cor",  insertable=false)
        var proCor : Int?=null,

        @Id
        @Column(name="pro_id")
        var proId : String = "",

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name= "sta_id")
        @OnDelete(action = OnDeleteAction.CASCADE)
        var state :  State?=null,

        @Column(name = "name")
        var proName : String = ""
)
{
        override fun toString(): String = "Province{proCor=$proCor,proId = $proId , proName = $proName, $state}"
}