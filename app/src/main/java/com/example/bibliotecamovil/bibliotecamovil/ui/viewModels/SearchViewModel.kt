package com.example.bibliotecamovil.bibliotecamovil.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.BestSellerAPIClient
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.Book
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.BookAPIClient
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import java.lang.Exception
import java.util.concurrent.Callable

class SearchViewModel(private val bookList: BookAPIClient, private val bestSellerList: BestSellerAPIClient) : ViewModel() {
    private val searchedBooks = MutableLiveData<MutableList<Book>>()
    val errorMessage = MutableLiveData<String>()
    val loadingMovies = MutableLiveData<Boolean>()



    fun getSearchedBooks(): MutableLiveData<MutableList<Book>> {
        return this.searchedBooks
    }

    class Factory() : ViewModelProvider.NewInstanceFactory() {
        // Disclaimer esto es medio termidor
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SearchViewModel(BookAPIClient(), BestSellerAPIClient()) as T
        }
    }


    fun getBooks(query: String) {
        loadingMovies.value = true
        viewModelScope.launch {
            try {
                val response = bookList.getLibros(query)
                if (response.isSuccessful && response.body() != null) {
                    val books = response.body()!!
                    if (books.items != null) {
                        searchedBooks.value = books.items
                        loadingMovies.value = false
                    } else {
                        searchedBooks.value = mutableListOf()
                    }
                } else {
                    val error = response.errorBody().toString()
                    errorMessage.value = error
                }
            } catch (e: Exception) {
                Firebase.crashlytics.recordException(e)
                Firebase.crashlytics.log("No respondió la API")
                errorMessage.value = e.message
            }

        }
    }

     fun setBooks(){
        viewModelScope.launch{
            try{
                val lista = mutableListOf<Book>()
                val response = bestSellerList.getBestSeller("hardcover-fiction")
                if (response.isSuccessful && response.body() != null) {
                    val books = response.body()!!
                    if(books.results.libros != null) {

                        for(book in books.results.libros) {
                            bookList.getLibros(book.titulo).body()?.items?.get(0)
                                ?.let { lista.add(it) }
                        }
                        searchedBooks.value = lista

                    }
                    else{
                        searchedBooks.value = mutableListOf()
                }
                }
                else{
                    val error = response.errorBody().toString()
                    errorMessage.value = error
                }
            }catch (e:Exception){
                errorMessage.value = e.message
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