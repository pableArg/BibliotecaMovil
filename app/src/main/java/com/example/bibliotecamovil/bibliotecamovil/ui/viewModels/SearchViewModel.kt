package com.example.bibliotecamovil.bibliotecamovil.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.Book
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.BookAPI
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.BookByAPI
import com.example.bibliotecamovil.bibliotecamovil.domain.model.BookResponse
import kotlinx.coroutines.*

class SearchViewModel(private val bookList: BookByAPI) : ViewModel() {
     val searchedBooks = MutableLiveData<BookResponse>()
    val errorMessage = MutableLiveData<String>()

    fun getBooks(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = bookList.getLibros(query)
            if (response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main) {
                    val movies = response.body()!!
                    searchedBooks.value = movies
                }
            } else {
                val error = response.errorBody().toString()
                errorMessage.value = error
            }
        }
    }
    /*fun getBooks() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = bookList.getLibros()
            if (response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main) {
                    searchedBooks.value = response.body()
                }
            } else {
                errorMessage.value = response.errorBody().toString()
            }
        }
    }*/


}