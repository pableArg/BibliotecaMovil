package com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit

import com.example.bibliotecamovil.bibliotecamovil.domain.model.BookResponse
import com.google.gson.Gson
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BookByAPI {

    private fun getAPI(): BookAPI {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .baseUrl("https://www.googleapis.com/books/v1/")
            .build();
        return retrofit.create(BookAPI::class.java)
    }

    fun searchLibro(id: String, callback: Callback<Book>) {
        getAPI().searchLibro(id).enqueue(callback)
    }
    fun getLibros(nombreLibro: String, callback: Callback<BookResponse>){
        getAPI().getLibros(nombreLibro).enqueue(callback)
    }

}