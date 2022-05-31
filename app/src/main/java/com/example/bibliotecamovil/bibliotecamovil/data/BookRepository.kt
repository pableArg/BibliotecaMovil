package com.example.bibliotecamovil.bibliotecamovil.data

import com.example.bibliotecamovil.bibliotecamovil.data.database.BookFavDAO
import com.example.bibliotecamovil.bibliotecamovil.data.database.BookFavEntity
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.Book
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.BookAPIClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookRepository (private val api: BookAPIClient, private val bookDao: BookFavDAO){

    //Traigo lo que tengo en la Database y lo tranformo al modelo de dominio
    /* Hay que ver como hacer xq tenemos que pasar los ids por la API para traer
    los demas datos de cada libro
    */
    suspend fun getAllBooksFromDatabase():List<Book>{
        return formatResponse(bookDao.getAllBoksFavs())
    }

    suspend fun deleteBookFromDatabase(book: BookFavEntity){
         bookDao.delete(book)
    }

    private fun formatResponse(response: List<BookFavEntity>): List<Book> {
        val books= mutableListOf<Book>()
        for (bookFavEntity in response) {
            //corrutina
            CoroutineScope(Dispatchers.IO).launch {
                ///CREO QUE FALTA UNA VALIDACION DEL RESPONSE DEL API.SEARCH(...)
                api.searchLibro((bookFavEntity.id_book.toString())).body()?.let { books.add(it) }
            }
        }
        return books
    }

    suspend fun insertBookFav(bookFav: BookFavEntity){
        bookDao.insert(bookFav)
    }
    /*
    TRAIGO DE LA API Y LO TRANSFORMO AL MODELO DE DOMAIN
    suspend fun getAllBooksFromApi(): List<Book/*book_model_domain*/> {
        val response: List<QuoteModel> = api.getQuotes()
        return response.map { it.toDomain() }
    }*/
}