package com.example.bibliotecamovil.bibliotecamovil.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.Book
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.BookAPIClient
import kotlinx.coroutines.*
import java.lang.Exception
import java.util.concurrent.Callable

class SearchViewModel(private val bookList: BookAPIClient) : ViewModel() {
    private val searchedBooks = MutableLiveData<MutableList<Book>>()
    val errorMessage = MutableLiveData<String>()


    fun getSearchedBooks(): MutableLiveData<MutableList<Book>> {
        return this.searchedBooks
    }

    class Factory() : ViewModelProvider.NewInstanceFactory() {
        // Disclaimer esto es medio termidor
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SearchViewModel(BookAPIClient()) as T
        }
    }


    fun getBooks(query: String) {
        viewModelScope.launch {
            try {
                val response = bookList.getLibros(query)
                if (response.isSuccessful && response.body() != null) {
                    val books = response.body()!!
                    if (books.items != null) {
                        searchedBooks.value = books.items
                    } else {
                        searchedBooks.value = mutableListOf()
                    }
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