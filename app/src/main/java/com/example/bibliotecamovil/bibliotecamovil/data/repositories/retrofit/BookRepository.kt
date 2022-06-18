package com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit

import com.example.bibliotecamovil.bibliotecamovil.data.database.BookFavEntity
import com.example.bibliotecamovil.bibliotecamovil.data.database.LibraryFavDatabase
import retrofit2.Response

class BookRepository (private val api: BookAPIClient, private val database : LibraryFavDatabase) {
// prebuntar si eesta bien database.bookFavDao.getAlll o si directamente inyectamos el DAO.
   suspend fun getAllBooksFromDatabase(): MutableList<String> {
        val idList = mutableListOf<String>()
        for (BookFavEntity in database.bookFavDao().getAllBoksFavs()) {
            idList.add(BookFavEntity.id_book)
        }
        return idList
    }

    suspend fun deleteBookFromDatabase(book: BookFavEntity) {
        database.bookFavDao().delete(book)
    }

    suspend fun insertBookFav(bookFav: BookFavEntity) {
        database.bookFavDao().insert(bookFav)
    }

    suspend fun getBooksById(idBook: String): Response<Book> {
        return api.searchLibro(idBook)
    }
}