package com.example.bibliotecamovil.bibliotecamovil.domain.model

import com.example.bibliotecamovil.bibliotecamovil.data.BookRepository
import com.example.bibliotecamovil.bibliotecamovil.data.database.BookFavEntity

class VerificarEnFavoritos(private val bookRepository: BookRepository) {

    fun intoFavs(idBook: String): Boolean{
        var result = false
        if(bookRepository.getAllBooksFromDatabase().contains(idBook)){
            result = true
        }
        return result
    }

    suspend fun addOrDeleteNewMovieFav(idBook: String){
        if(intoFavs(idBook)){
            bookRepository.deleteBookFromDatabase(BookFavEntity(idBook))
        }else{
            bookRepository.insertBookFav(BookFavEntity(idBook))
        }
    }
}
/*ACTIVITY DETAILS
* private fun setFavIcon() {
        if(movieDetailsViewModel.movieIntoFavList(getMovieId())){
            fav.background = getDrawable(R.drawable.ic_fav_pressed)
        }else{
            fav.background = getDrawable(R.drawable.ic_fav_default)
        }
    }
    *
   private fun addOrDeleteMovieOfFavList() {
        movieDetailsViewModel.addOrDeleteNewMovieFav(getMovieId())
        setFavIcon()
    }
  VIEWMODEL DETAILS
  fun movieIntoFavList(movieId: Int): Boolean {
        return favEntityRepository.movieIdIntoFavList(movieId)
    }
    *
   fun addOrDeleteNewMovieFav(movieId: Int, userId: Int) {
        favEntityRepository.addOrDeleteNewMovieFav(movieId)
    }
 */