package com.example.bibliotecamovil.bibliotecamovil.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.Book
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.BookAPIClient
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.VolumeInfo
import com.example.bibliotecamovil.bibliotecamovil.ui.fragments.DetailFragmentArgs
import com.example.bibliotecamovil.bibliotecamovil.ui.fragments.FavouriteFragment
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import java.lang.Exception

class DetailViewModel(private val bookAPI : BookAPIClient) : ViewModel() {
    private lateinit var bookDetail : MutableLiveData<Book>


    class Factory() : ViewModelProvider.NewInstanceFactory() {
        // Disclaimer esto es medio termidor
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return DetailViewModel(BookAPIClient()) as T
        }
    }

    fun getBook() : MutableLiveData<Book>{
        return this.bookDetail
    }

    fun iterarLibro(idBook : String){

        viewModelScope.launch {
            try {
                val response = bookAPI.searchLibro(idBook)
                if (response.isSuccessful && response.body() != null) {
                    val book = response.body()!!
                    if (book != null) {
                        bookDetail.value =  book
                    } else {

                    }
                }
            } catch (e: Exception) {
                Firebase.crashlytics.recordException(e)
                Firebase.crashlytics.log("No respondió la API")
            }
        }
    }
}