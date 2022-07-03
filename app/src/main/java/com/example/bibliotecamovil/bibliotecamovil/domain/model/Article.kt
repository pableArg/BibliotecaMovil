package com.example.bibliotecamovil.bibliotecamovil.domain.model

import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("id")
    var id : String,
    @SerializedName("title")
    var titulo : String,
    @SerializedName("price")
    var precio : Double,
    @SerializedName("condition")
    var condicion : String,
    @SerializedName("available_quantity")
    var cantidadDisponible : Int,
    @SerializedName("thumbnail")
    var imagen : String,
    @SerializedName("thumbnail_id")
    var imagen_id : String,
    @SerializedName("permalink")
    var urlArticulo : String
)

