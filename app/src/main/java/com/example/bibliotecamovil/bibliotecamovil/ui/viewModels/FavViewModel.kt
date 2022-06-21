package com.example.bibliotecamovil.bibliotecamovil.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bibliotecamovil.bibliotecamovil.data.database.BookFavEntity
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.Book
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.BookRepository
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavViewModel(private val bookRepository: BookRepository) : ViewModel() {
    val booksFavLiveData = MutableLiveData<MutableList<Book>>()
    var idFavoritos: MutableList<String> = emptyList<String>().toMutableList()
    private var booksList: MutableList<Book> = emptyList<Book>().toMutableList()
    val errorMessage = MutableLiveData<String>()


    private fun updateBooksLiveData(bookIDList: List<String>) {
        viewModelScope.launch {
            try {
                for (bookId in bookIDList) {
                    val response = bookRepository.searchLibro(bookId)
                    if (response.isSuccessful && response.body() != null) {
                        val book = response.body()!!
                        booksList.add(book)
                        booksFavLiveData.value = booksList

                    } else {
                        val error = response.errorBody().toString()
                        errorMessage.value = error
                    }
                }

            } catch (e: Exception) {
                Firebase.crashlytics.recordException(e)
                errorMessage.value = e.message
            }
        }
    }

    fun setupBookDataBase() {
        viewModelScope.launch {
            idFavoritos = bookRepository.getAllBooksFromDatabase()
            updateBooksLiveData(idFavoritos)
        }
    }

    fun deleteOrInsert(book: Book) {
        viewModelScope.launch {
            if (idFavoritos.contains(book.id)) {
                bookRepository.deleteBookFromDatabase(BookFavEntity(book.id))
                idFavoritos.remove(book.id)
                booksFavLiveData.value?.remove(book)
            } else {
                bookRepository.insertBookInDatabase(BookFavEntity(book.id))
                idFavoritos.add(book.id)
                booksFavLiveData.value?.add(book)
            }
        }
    }
    //hacer las otras cosas.
}
