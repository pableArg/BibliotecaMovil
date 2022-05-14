package com.example.bibliotecamovil.bibliotecamovil.domain.model

import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("id")
    var id : Int,
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
    @SerializedName("category_id")
    var categoria_id : String,
    @SerializedName("currency_id")
    var moneda_id : String,
    @SerializedName("shipping")
    var envio : Shipping
)

data class Shipping(
    @SerializedName("free_shipping")
    var envio_gratis : Boolean,
    @SerializedName("mode")
    var modo : String,
    @SerializedName("tags")
    var etiquetas : ArrayList<String>,
    @SerializedName("logistic_type")
    var logistica : String,
    @SerializedName("store_pick_up")
    var buscarEnLocal : Boolean
)
