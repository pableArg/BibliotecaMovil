package com.example.bibliotecamovil.bibliotecamovil.data.repositories.retofit

import com.example.bibliotecamovil.bibliotecamovil.domain.model.ArticleResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ArticleAPIClient {

    private val serviceGetArticleByName : BookAPI = Retrofit.Builder()
        .baseUrl("https://api.mercadolibre.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(BookAPI::class.java)

        suspend fun getArticulos(nombreArt: String) : Response<ArticleResponse> {
            return serviceGetArticleByName.getArticulos(nombreArt)
}
}