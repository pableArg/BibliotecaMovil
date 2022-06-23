package com.example.bibliotecamovil.bibliotecamovil.data.repositories.retofit

import androidx.lifecycle.MutableLiveData
import com.example.bibliotecamovil.bibliotecamovil.domain.model.BookResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BookAPIClient {
    val errorMessage = MutableLiveData<String>()

    private val serviceGetBookByAPI: BookAPI = Retrofit.Builder()
        .baseUrl("https://www.googleapis.com/books/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(BookAPI::class.java)


    suspend fun searchLibro(bookId: String): Response<Book> {
        return serviceGetBookByAPI.searchLibro(bookId)
    }

    suspend fun getLibros(nombreLibro: String): Response<BookResponse> {
        return serviceGetBookByAPI.getLibros(nombreLibro)
    }

}