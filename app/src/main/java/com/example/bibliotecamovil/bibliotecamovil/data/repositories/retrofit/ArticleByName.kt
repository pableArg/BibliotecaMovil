package com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit

import com.example.bibliotecamovil.bibliotecamovil.domain.model.ArticleResponse
import com.google.gson.Gson
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ArticleByName {

    private fun getAPI(): BookAPI {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .baseUrl("https://api.mercadolibre.com/")
            .build();
        return retrofit.create(BookAPI::class.java)
    }

    fun getArticulos(nombreArt: String, callback: Callback<ArticleResponse>){
        getAPI().getArticulos(nombreArt).enqueue(callback)
    }
}