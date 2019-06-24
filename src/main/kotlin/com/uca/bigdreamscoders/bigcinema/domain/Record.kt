package com.uca.bigdreamscoders.bigcinema.domain

import java.util.*
import javax.persistence.*

@Entity(name = "record")
data class Record(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "record_rec_cor_seq")
        @SequenceGenerator(sequenceName = "record_rec_cor_seq",  name = "record_rec_cor_seq")
        @Column(name = "rec_cor")
        var recCor : Int?=null,

        @Column(name="rec_id")
        var recId : String = "",

        @Column(name="description")
        var description : String ="",

        @Column(name = "active_status")
        var actStatus : Boolean?=false,

        @Column(name = "creation_date")
        var creationDate : String?="",

        @Column(name = "creator_account")
        var creatorAccount : String ="",

        @Column(name = "last_modification")
        var lastModification : String?="",

        @Column(name = "last_to_modify")
        var lastToModify : String =""
)
{
    override fun toString(): String = "Province{cor=$recCor, id = $recId , description = $description, " +
            "status = $actStatus, creationDate=$creationDate, creatorAccount = $creatorAccount," +
            "lastModification=$lastModification, lastToModify=$lastToModify}"
}