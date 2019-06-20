package com.uca.bigdreamscoders.bigcinema.domain


import javax.persistence.*

@Entity(name="movie")
data class Movie (
        @Id
        @GeneratedValue
        @Column(name = "mov_cor")
        var movCor : Int,

        @Column(name="mov_id")
        var movId : String = "",

        @Column(name = "name")
        var name : String = "",

        @Column(name = "active_status")
        var actStatus : Boolean,

        @Column(name = "description")
        var description : String = "",

        @Column(name = "image_url")
        var imageUrl : String = ""
)
{
    override fun toString(): String = "Movie{cor=$movCor" +
            "id = $movId , name = $name, actStatus = $actStatus," +
            "description = $description, imageUrl = $imageUrl}"
}