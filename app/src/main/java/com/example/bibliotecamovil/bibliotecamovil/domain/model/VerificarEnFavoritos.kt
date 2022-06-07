package com.example.bibliotecamovil.bibliotecamovil.domain.model

import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.BookRepository
import com.example.bibliotecamovil.bibliotecamovil.data.database.BookFavEntity

class VerificarEnFavoritos(private val bookRepository: BookRepository) {

    fun intoFavs(idBook: String): Boolean {
        return bookRepository.getAllBooksFromDatabase().contains(idBook)
    }

    suspend fun addOrDeleteNewMovieFav(idBook: String) {
        if (intoFavs(idBook)) {
            bookRepository.deleteBookFromDatabase(BookFavEntity(idBook))
        } else {
            bookRepository.insertBookFav(BookFavEntity(idBook))
        }
    }
}
