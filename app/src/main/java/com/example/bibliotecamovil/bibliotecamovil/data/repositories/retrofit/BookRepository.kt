package com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit

import com.example.bibliotecamovil.bibliotecamovil.data.database.BookFavDAO
import com.example.bibliotecamovil.bibliotecamovil.data.database.BookFavEntity
import com.example.bibliotecamovil.bibliotecamovil.data.database.LibraryFavDatabase
import retrofit2.Response

class BookRepository (private val api: BookAPIClient, private val database : BookFavDAO) {
// prebuntar si eesta bien database.bookFavDao.getAlll o si directamente inyectamos el DAO.
   suspend fun getAllBooksFromDatabase(): MutableList<String> {
        val idList = mutableListOf<String>()
        for (BookFavEntity in database.getAllBoksFavs()) {
            idList.add(BookFavEntity.id_book)
        }
        return idList
    }

    suspend fun deleteBookFromDatabase(idBook: String) {
        database.delete(BookFavEntity(idBook))
    }

    suspend fun insertBookInDatabase(idBook: String) {
        database.insert(BookFavEntity(idBook))
    }

    suspend fun getBooksById(idBook: String): Response<Book> {
        return api.searchLibro(idBook)
    }
}