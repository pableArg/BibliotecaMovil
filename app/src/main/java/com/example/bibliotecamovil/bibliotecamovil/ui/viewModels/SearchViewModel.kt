package com.example.bibliotecamovil.bibliotecamovil.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.bibliotecamovil.bibliotecamovil.data.database.BookFavDAO
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.BestSellerAPIClient
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.Book
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.BookAPIClient
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.BookRepository
import kotlinx.coroutines.*
import java.lang.Exception
import java.util.concurrent.Callable

class SearchViewModel(private val bookRepository: BookRepository) : ViewModel() {
    private val searchedBooks = MutableLiveData<MutableList<Book>>()

    val errorMessage = MutableLiveData<String>()

    fun getSearchedBooks(): MutableLiveData<MutableList<Book>>{
        return this.searchedBooks
    }
/*
    class Factory() : ViewModelProvider.NewInstanceFactory() {
        // Disclaimer esto es medio termidor
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SearchViewModel(BookRepository(BookAPIClient(), BestSellerAPIClient(),)) as T
        }
    }
*/

    fun getBooks(nameBook: String) {
        viewModelScope.launch {
            try {
                val response = bookRepository.searchBooksByName(nameBook)
                if (response.isSuccessful && response.body() != null) {
                    val books = response.body()!!
                    if(books.items != null) {
                        searchedBooks.value = books.items
                    }else{
                        searchedBooks.value = mutableListOf()
                    }
                } else {
                    val error = response.errorBody().toString()
                    errorMessage.value = error
                }
            }catch (e:Exception){
                errorMessage.value = e.message
            }

        }
    }

     fun setBooks(){
         viewModelScope.launch {
             try {
                 val lista = mutableListOf<Book>()
                 val response = bookRepository.searchBestSeller("hardcover-fiction")
                 if (response.isSuccessful && response.body() != null) {
                     val books = response.body()!!
                     if (books.results.libros != null) {

                             for (book in books.results.libros) {
                                 bookRepository.searchBooksByName(book.titulo).body()?.items?.get(0)
                                     ?.let { lista.add(it) }
                             }

                         searchedBooks.value = lista

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