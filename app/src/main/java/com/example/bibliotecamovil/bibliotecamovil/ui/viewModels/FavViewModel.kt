package com.example.bibliotecamovil.bibliotecamovil.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retofit.Book
import com.example.bibliotecamovil.bibliotecamovil.data.BookRepository
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*

class FavViewModel(private val bookRepository: BookRepository) : ViewModel() {
    val booksFavLiveData = MutableLiveData<MutableList<Book>>()
    var idFavoritosLiveData = MutableLiveData<MutableList<String>>()
    private var booksList = mutableListOf<Book>()
     var idFavorite = mutableListOf<String>()
    private val errorMessage = MutableLiveData<String>()

    private fun updateBooksLiveData(bookIDList: MutableList<String>) {
        viewModelScope.launch {
            try {
                idFavoritosLiveData.value = bookIDList
                for (bookId in bookIDList) {
                    val response = bookRepository.searchBookById(bookId)
                    if (response.isSuccessful && response.body() != null) {
                        val book = response.body()!!
                        booksList.add(book)
                    } else {
                        val error = response.errorBody().toString()
                        errorMessage.value = error
                    }
                }
                booksFavLiveData.value = booksList
            } catch (e: Exception) {
                Firebase.crashlytics.recordException(e)
                errorMessage.value = e.message
            }
        }
    }

    fun setupBookDataBase() {
        CoroutineScope(Dispatchers.IO).launch {
            idFavorite = bookRepository.getAllBooksFromDatabase()
            updateBooksLiveData(idFavorite)
        }
    }

    fun deleteOrInsert(book: Book): Boolean {
        return if (idFavoritosLiveData.value?.contains(book.id) == true) {
            removeS(book)
            false
        } else {
            insert(book)
            true
        }
    }

    private fun insert(book: Book) {
        CoroutineScope(Dispatchers.IO).launch {
            bookRepository.insertBookInDatabase(book.id)
        }
        idFavorite.add(book.id)
        idFavoritosLiveData.value = idFavorite
        booksList.add(book)
        booksFavLiveData.value = booksList
    }
    private fun removeS(book: Book) {
        CoroutineScope(Dispatchers.IO).launch {
            bookRepository.deleteBookFromDatabase(book.id)
        }
        idFavorite.remove(book.id)
        idFavoritosLiveData.value = idFavorite
        booksList.remove(book)
        booksFavLiveData.value = booksList
    }

    fun cleanLists() {
        idFavoritosLiveData.value?.clear()
        idFavorite.clear()
        booksList.clear()
        booksFavLiveData.value?.clear()
    }
}
