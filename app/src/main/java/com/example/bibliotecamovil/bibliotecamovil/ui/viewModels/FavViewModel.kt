package com.example.bibliotecamovil.bibliotecamovil.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.bibliotecamovil.bibliotecamovil.data.database.BookFavEntity
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.Book
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.BookAPIClient
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.BookRepository
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavViewModel(/*BookRepository*/) : ViewModel() {
    val booksFavLiveData = MutableLiveData<MutableList<Book>>()
    var idFavoritos: MutableList<String> = emptyList<String>().toMutableList()
    private var booksList: MutableList<Book> = emptyList<Book>().toMutableList()
    val errorMessage = MutableLiveData<String>()
    private val bookRepository: BookAPIClient = BookAPIClient()

    fun getFavBooks(): MutableLiveData<MutableList<Book>> {
        return this.booksFavLiveData
    }

    class Factory() : ViewModelProvider.NewInstanceFactory() {
        // Disclaimer esto es medio termidor
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FavViewModel() as T
        }
    }

    fun updateBooksLiveData(bookIDList: List<String>) {
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
            idFavoritos = mutableListOf(
                "s1gVAAAAYAAJ",
                "s1gVAAAAYAAf",
                "s1gVAAAAYAAt",
                "s1gVAAAAYAAp"
            )  /*bookRepository.getAllBooksFromDatabase()*/
            updateBooksLiveData(idFavoritos)
        }
    }

    fun deleteOrInsert(idBook: String): Boolean {
        var result = false
        viewModelScope.launch {

            if (idFavoritos.contains(idBook)) {
                //bookRepository.deleteBookFromDatabase(BookFavEntity(idBook))
                idFavoritos.remove(idBook)
                result = false
            } else {
                //bookRepository.insertBookFav(BookFavEntity(idBook))
                idFavoritos.add(idBook)
                result = true
            }
        }
        return result
        this.setupBookDataBase()
    }
    //hacer las otras cosas.
}
