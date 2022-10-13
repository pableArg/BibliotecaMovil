package com.example.bibliotecamovil.bibliotecamovil.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retofit.Book
import com.example.bibliotecamovil.bibliotecamovil.data.BookRepository
import kotlinx.coroutines.*
import java.lang.Exception

class SearchViewModel(private val bookRepository: BookRepository) : ViewModel() {
    private val searchedBooks = MutableLiveData<MutableList<Book>>()
    private val errorMessage = MutableLiveData<String>()
    var text = "Best Sellers"

    fun getSearchedBooks(): MutableLiveData<MutableList<Book>>{
        return this.searchedBooks
    }


    fun setBooks(nameBook: String) {
        text = nameBook
        viewModelScope.launch {
            try {
                val response = bookRepository.searchBooksByName(nameBook)
                if (response.isSuccessful && response.body() != null) {
                    val books = response.body()!!
                    searchedBooks.value = books.items
                } else {
                    val error = response.errorBody().toString()
                    errorMessage.value = error
                }
            }catch (e:Exception){
                errorMessage.value = e.message
            }

        }
    }
    fun setBetseller() {
        viewModelScope.launch {
            try {
                val response = bookRepository.searchBooksByName("s1gVAAAAYAAJ")
                if (response.isSuccessful && response.body() != null) {
                    val books = response.body()!!
                    searchedBooks.value = books.items
                } else {
                    val error = response.errorBody().toString()
                    errorMessage.value = error
                }
            }catch (e:Exception){
                errorMessage.value = e.message
            }

        }
    }





}