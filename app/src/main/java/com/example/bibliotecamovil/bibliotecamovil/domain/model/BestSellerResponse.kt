package com.example.bibliotecamovil.bibliotecamovil.domain.model

import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retofit.Book
import com.google.gson.annotations.SerializedName

class BestSellerResponse(val results : BestSellers)

data class BestSellers(
    @SerializedName ("list_name")
    val nombre_lista : String,
    @SerializedName ("published_date")
    val fecha_publicacion : String,
    @SerializedName ("books")
    val libros : MutableList<Book>
)

