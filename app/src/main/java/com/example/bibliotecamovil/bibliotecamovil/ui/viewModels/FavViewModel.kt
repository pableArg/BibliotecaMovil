package com.example.bibliotecamovil.bibliotecamovil.ui.viewModels

import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bibliotecamovil.R
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retofit.Book
import com.example.bibliotecamovil.bibliotecamovil.data.BookRepository
import com.example.bibliotecamovil.databinding.FragmentDetailBinding
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext

class FavViewModel(private val bookRepository: BookRepository) : ViewModel() {
    val booksFavLiveData = MutableLiveData<MutableList<Book>>()
    var idFavoritosLiveData= MutableLiveData<MutableList<String>>()
    private var booksList = mutableListOf<Book>()
    var idFavoritos = mutableListOf<String>()
    val errorMessage = MutableLiveData<String>()


    private fun updateBooksLiveData(bookIDList: MutableList<String>) {
        viewModelScope.launch {
            try {
                idFavoritosLiveData.value=bookIDList
                for (bookId in bookIDList) {
                    val response = bookRepository.searchBookById(bookId)
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
        CoroutineScope(Dispatchers.IO).launch{
            idFavoritos = bookRepository.getAllBooksFromDatabase()
            updateBooksLiveData(idFavoritos)
        }
    }

    fun deleteOrInsert(book: Book) : Boolean{
        return if (idFavoritos.contains(book.id)) {
            remove(book)
            false
        } else {
            insert(book)
            true
        }

    }

    private fun insert(book: Book){
        CoroutineScope(Dispatchers.IO).launch {
            bookRepository.insertBookInDatabase(book.id)
            idFavoritos.add(book.id)
            idFavoritosLiveData.value?.add(book.id)
            booksFavLiveData.value?.add(book)
        }
    }

    private fun remove(book: Book){
        CoroutineScope(Dispatchers.IO).launch {
            bookRepository.deleteBookFromDatabase(book.id)
            idFavoritos.remove(book.id)
            idFavoritosLiveData.value?.remove(book.id)
            booksFavLiveData.value?.remove(book)
        }

    }
    fun deleteListBooks(){
        booksList = mutableListOf()
    }

}
