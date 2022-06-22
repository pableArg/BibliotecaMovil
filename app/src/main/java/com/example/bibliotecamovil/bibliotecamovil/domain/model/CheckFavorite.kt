package com.example.bibliotecamovil.bibliotecamovil.domain.model

import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.Book
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.FavViewModel

class CheckFavorite (private val favViewModel: FavViewModel) {
    //private val bookRepository: BookRepository

    fun intoFavs(idBook: String): Boolean {
        return favViewModel.idFavoritos.contains(idBook)
    }

     fun addOrDeleteNewMovieFav(book: Book) {
        favViewModel.deleteOrInsert(book)
    }
}