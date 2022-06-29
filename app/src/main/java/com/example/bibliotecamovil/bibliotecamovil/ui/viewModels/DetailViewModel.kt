package com.example.bibliotecamovil.bibliotecamovil.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retofit.Book

class DetailViewModel() : ViewModel() {
    val bookDetail = MutableLiveData<Book>()


}