package com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit

import com.example.bibliotecamovil.bibliotecamovil.domain.model.ArticleResponse
import com.example.bibliotecamovil.bibliotecamovil.domain.model.BookResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookAPI {

    companion object{
    const val API_KEY : String = "AIzaSyBoE2iyzGrxpXZ0USCZbSzFUmRhKM224B4"
    const val SITE_ID : String = "MLA"
    }

    //Obtengo los datos de los libros listados por el nombre
    @GET("volumes")
    fun getLibros(@Query("q") nombreLibro: String): Call<BookResponse>

    //Obtengo los datos de un solo libro por id
    @GET("volumes/{id}?&key=$API_KEY")
    fun searchLibro(@Path("id") id: String): Call<Book>

    //Obtengo el listado de los articulos por nombre
    @GET("sites/MLA/search")
    fun getArticulos(@Query("q") nombreArt: String): Call<ArticleResponse>

}