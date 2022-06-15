package com.example.bibliotecamovil.bibliotecamovil.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.Book
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.BookAPIClient
import kotlinx.coroutines.*
import java.lang.Exception

class SearchViewModel(private val bookAPIClient: BookAPIClient) : ViewModel() {
    private val searchedBooks = MutableLiveData<MutableList<Book>>()
    private val errorMessage = MutableLiveData<String>()
    private val loadingMovies = MutableLiveData<Boolean>()

    class Factory() : ViewModelProvider.NewInstanceFactory() {
        // Disclaimer esto es medio termidor
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SearchViewModel(BookAPIClient()) as T
        }
    }

    fun getBooks(): MutableLiveData<MutableList<Book>> {
        return this.searchedBooks
    }

    fun retrieveBooks(bookName: String) {
        loadingMovies.value = true
        viewModelScope.launch {
            try {
                val response = bookAPIClient.getLibros(bookName)
                if (response.isSuccessful && response.body() != null) {
                    val books = response.body()!!
                    searchedBooks.value = books.items
                    loadingMovies.value = false
                } else {
                    val error = response.errorBody().toString()
                    errorMessage.value = error
                }
            } catch (e: Exception) {
                errorMessage.value = e.message
            }
        }
    }


}