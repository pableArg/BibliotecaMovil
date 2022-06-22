package com.example.bibliotecamovil.bibliotecamovil.domain.model

import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.Book
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.DetailViewModel
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.FavViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class CheckFavorite () {
    //private val favViewModel by sharedViewModel<FavViewModel>()
    //private val bookRepository: BookRepository

    fun intoFavs(idBook: String): Boolean {
        //return favViewModel.idFavoritos.contains(idBook)
        return false
    }

     fun addOrDeleteNewMovieFav(book: Book) {
        //favViewModel.deleteOrInsert(book)
    }
}