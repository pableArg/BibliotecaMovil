package com.example.bibliotecamovil.bibliotecamovil.data.repositories.retofit

import com.example.bibliotecamovil.bibliotecamovil.domain.model.ArticleResponse
import com.example.bibliotecamovil.bibliotecamovil.domain.model.BestSellerResponse
import com.example.bibliotecamovil.bibliotecamovil.domain.model.BookResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookAPI {

    companion object {
        const val API_KEY: String = "AIzaSyBoE2iyzGrxpXZ0USCZbSzFUmRhKM224B4"
        const val API_KEY_NY: String = "I1L48Z5qAJdSdgOiLL5YJfjf8oadEb6c"
        const val SITE_ID: String = "MLA"
    }

    //Obtengo los datos de los libros listados por el nombre
    @GET("volumes")
    suspend fun getLibros(
        @Query("q") nombre: String,
        @Query("maxResults") resultados: String = "35"
    ): Response<BookResponse>

    //Obtengo los datos de un solo libro por id
    @GET("volumes/{id}?&key=$API_KEY")
    suspend fun searchLibro(@Path("id") id: String): Response<Book>

    //Obtengo el listado de los articulos por nombre
    @GET("sites/$SITE_ID/search")
    suspend fun getArticulos(@Query("q") nombreArt: String): Response<ArticleResponse>

    @GET("{name}.json?api-key=$API_KEY_NY")
    suspend fun getBestSellers(@Path("name") nombreLista: String): Response<BestSellerResponse>
}