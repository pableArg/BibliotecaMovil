package com.example.bibliotecamovil.bibliotecamovil.data.repositories.retofit

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
    var libroInfo : VolumeInfo,
    @SerializedName("saleInfo")
    val libroVenta : SaleInfo
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
    var descripcion : String
)

data class SaleInfo(
    @SerializedName("country")
    var pais : String
)
