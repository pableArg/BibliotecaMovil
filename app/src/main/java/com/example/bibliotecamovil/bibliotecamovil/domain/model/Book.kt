package com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit

import com.google.gson.annotations.SerializedName

data class Book(
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
    var descripcion : String,
    @SerializedName("pageCount")
    val paginas : Int,
    @SerializedName("imageLinks")
    var Imagenes : ImageLink
)

data class SaleInfo(
    @SerializedName("country")
    var pais : String,
    @SerializedName("list_price")
    var precioLista : ListPrice,
    @SerializedName("retail_price")
    var precioMenor : RetailPrice
)
data class ListPrice(
    @SerializedName("amount")
    var monto : Double
)
data class RetailPrice(
    @SerializedName("amount")
    var monto : Double
)

data class ImageLink(
    @SerializedName("smallThumbnail")
    var icono : String,
    @SerializedName("thumbnail")
    var imagen : String
)
