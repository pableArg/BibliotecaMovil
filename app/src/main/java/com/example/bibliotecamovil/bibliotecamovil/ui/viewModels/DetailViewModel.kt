package com.example.bibliotecamovil.bibliotecamovil.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bibliotecamovil.bibliotecamovil.data.BookRepository
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retofit.Book

class DetailViewModel() : ViewModel() {
    val bookDetail = MutableLiveData<Book>()


    class Factory() : ViewModelProvider.NewInstanceFactory() {
        // Disclaimer esto es medio termidor
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return DetailViewModel() as T
        }
    }

}