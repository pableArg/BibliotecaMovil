package com.example.bibliotecamovil.bibliotecamovil.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.Book
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavViewModel(private val bookRepository: BookRepository) : ViewModel() {
    val booksFavLiveData = MutableLiveData<MutableList<Book>>()
    private var booksList: MutableList<Book> = emptyList<Book>().toMutableList()
    val errorMessage = MutableLiveData<String>()


    fun getFavBooks(): MutableLiveData<MutableList<Book>> {
        return this.booksFavLiveData
    }

    fun updateBooksLiveData(bookIDList: List<String>) {
        viewModelScope.launch {
            for (bookId in bookIDList) {
                val response = bookRepository.getBooksById(bookId)
                if (response.isSuccessful && response.body() != null) {
                    val book = response.body()!!
                    booksList.add(book)
                    withContext(Dispatchers.Main) {
                        booksFavLiveData.value = booksList
                    }
                } else {
                    val error = response.errorBody().toString()
                    errorMessage.value = error
                }
            }
        }
    }

    fun getBooksFavIDList(): List<String> {
        return bookRepository.getAllBooksFromDatabase()
    }
}