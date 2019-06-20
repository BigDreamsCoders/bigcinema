package com.uca.bigdreamscoders.bigcinema.domain

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity(name = "record")
data class Record(
        @Id
        @GeneratedValue
        @Column(name = "rec_cor")
        var recCor : Int,

        @Column(name="rec_id")
        var recId : String = "",

        @Column(name="description")
        var description : String ="",

        @Column(name = "active_status")
        var actStatus : Boolean,

        @Column(name = "creation_date")
        var creationDate : Date,

        @Column(name = "creator_account")
        var creatorAccount : String ="",

        @Column(name = "last_modification")
        var lastModification : Date,

        @Column(name = "last_to_modify")
        var lastToModify : String =""
)
{
    override fun toString(): String = "Province{cor=$recCor, id = $recId , description = $description, " +
            "status = $actStatus, creationDate=$creationDate, creatorAccount = $creatorAccount," +
            "lastModification=$lastModification, lastToModify=$lastToModify}"
}