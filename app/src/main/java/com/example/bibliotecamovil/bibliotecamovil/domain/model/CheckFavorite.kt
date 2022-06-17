package com.example.bibliotecamovil.bibliotecamovil.domain.model

import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.FavViewModel

class CheckFavorite (private val favViewModel: FavViewModel) {
    //private val bookRepository: BookRepository

    fun intoFavs(idBook: String): Boolean {
        return favViewModel.idFavoritos.contains(idBook)
    }

     fun addOrDeleteNewMovieFav(idBook: String) {
        favViewModel.deleteOrInsert(idBook)
    }
}