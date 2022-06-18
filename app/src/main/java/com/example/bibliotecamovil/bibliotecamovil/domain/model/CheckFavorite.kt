package com.example.bibliotecamovil.bibliotecamovil.domain.model

import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.Book
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.FavViewModel

class CheckFavorite (private val favViewModel: FavViewModel) {
    //private val bookRepository: BookRepository

    fun intoFavs(idBook: String): Boolean {
        return favViewModel.idFavoritos.contains(idBook)
    }
     fun addOrDeleteNewBookFav(idBook: String) {
        favViewModel.deleteOrInsert(idBook)
    }
    fun adOrDeleteNewMovieFav(book : Book) {
        if (favViewModel.deleteOrInsert(book.id)){
            favViewModel.booksFavLiveData.value?.add(book)
            favViewModel.idFavoritos.add(book.id)
        }else{
            favViewModel.booksFavLiveData.value?.remove(book)
            favViewModel.idFavoritos.remove(book.id)
        }

    }
}