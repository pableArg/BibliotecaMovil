package com.example.bibliotecamovil.bibliotecamovil.ui.viewModels

import androidx.lifecycle.MutableLiveData
import com.example.bibliotecamovil.bibliotecamovil.data.BookRepository
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.Book
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavViewModel(private val bookRepository: BookRepository) {
    val booksFavLiveData = MutableLiveData<MutableList<Book>>()
    private var booksList: MutableList<Book> = emptyList<Book>().toMutableList()
    val errorMessage = MutableLiveData<String>()

    fun updateBooksLiveData(bookIDList: List<String>) {
        CoroutineScope(Dispatchers.IO).launch {
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