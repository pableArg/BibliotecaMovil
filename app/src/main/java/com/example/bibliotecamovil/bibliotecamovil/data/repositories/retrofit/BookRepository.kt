package com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit

import com.example.bibliotecamovil.bibliotecamovil.data.database.BookFavDAO
import com.example.bibliotecamovil.bibliotecamovil.data.database.BookFavEntity
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.Book
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.BookAPIClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class BookRepository(private val api: BookAPIClient, private val bookDao: BookFavDAO) {

    fun getAllBooksFromDatabase(): List<String> {
        val idList = mutableListOf<String>()
        for (BookFavEntity in bookDao.getAllBoksFavs()) {
            idList.add(BookFavEntity.id_book)
        }
        return idList
    }

    suspend fun deleteBookFromDatabase(book: BookFavEntity) {
        bookDao.delete(book)
    }

    suspend fun insertBookFav(bookFav: BookFavEntity) {
        bookDao.insert(bookFav)
    }

    suspend fun getBooksById(idBook: String): Response<Book> {
        return api.searchLibro(idBook)
    }
}