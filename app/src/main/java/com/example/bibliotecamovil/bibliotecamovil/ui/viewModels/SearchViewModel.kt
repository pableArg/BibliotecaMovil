package com.example.bibliotecamovil.bibliotecamovil.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.Book
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.BookAPIClient
import kotlinx.coroutines.*
import java.util.concurrent.Callable

class SearchViewModel(private val bookList: BookAPIClient) : ViewModel() {
    val searchedBooks = MutableLiveData<List<Book>>()
    val errorMessage = MutableLiveData<String>()

    class Factory() : ViewModelProvider.NewInstanceFactory() {
        // Disclaimer esto es medio termidor
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SearchViewModel(BookAPIClient()) as T
        }
    }


    fun getBooks(query: String, callback: (brolis: MutableList<Book>) -> Unit ) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = bookList.getLibros(query)
            if (response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main) {
                    val books = response.body()!!
                    searchedBooks.value = books.items
                    callback(books.items)
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