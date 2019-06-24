package com.uca.bigdreamscoders.bigcinema.domain


import javax.persistence.*

@Entity(name="movie")
data class Movie (
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_mov_cor_seq")
        @SequenceGenerator(sequenceName = "movie_mov_cor_seq",  name = "movie_mov_cor_seq")
        @Column(name = "mov_cor")
        var movCor : Int?=null,

        @Column(name="mov_id")
        var movId : String = "",

        @Column(name = "name")
        var name : String = "",

        @Column(name = "active_status")
        var actStatus : Boolean?=false,

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