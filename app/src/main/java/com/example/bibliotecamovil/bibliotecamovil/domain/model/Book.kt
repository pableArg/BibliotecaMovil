package com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit

import com.google.gson.annotations.SerializedName

data class Book(
    @SerializedName ("rank")
    val rango : Int,
    @SerializedName ("title")
    val titulo : String,
    @SerializedName("kind")
    var kind : String,
    @SerializedName("id")
    var id : String,
    @SerializedName("volumeInfo")
    var libroInfo : VolumeInfo
)

data class VolumeInfo(

    @SerializedName("title")
    var titulo : String,
    @SerializedName("authors")
    var autores : ArrayList<String> ,
    @SerializedName("publisher")
    var editorial : String,
    @SerializedName("publishedDate")
    var fechaPublicacion : String,
    @SerializedName("description")
    var descripcion : String,
    @SerializedName("pageCount")
    val paginas : Int,
)


