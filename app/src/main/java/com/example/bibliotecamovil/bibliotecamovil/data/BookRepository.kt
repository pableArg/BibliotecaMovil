package com.example.bibliotecamovil.bibliotecamovil.data

import com.example.bibliotecamovil.bibliotecamovil.data.database.BookFavDAO
import com.example.bibliotecamovil.bibliotecamovil.data.database.BookFavEntity
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retofit.ArticleAPIClient
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retofit.BestSellerAPIClient
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retofit.Book
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retofit.BookAPIClient
import com.example.bibliotecamovil.bibliotecamovil.domain.model.Article
import com.example.bibliotecamovil.bibliotecamovil.domain.model.ArticleResponse
import com.example.bibliotecamovil.bibliotecamovil.domain.model.BestSellerResponse
import com.example.bibliotecamovil.bibliotecamovil.domain.model.BookResponse
import retrofit2.Response

class BookRepository (
    private val apiBook: BookAPIClient,
    private val apiBestSeller : BestSellerAPIClient,
    private val apiArticle : ArticleAPIClient,
    private val database : BookFavDAO) {

   fun getAllBooksFromDatabase(): MutableList<String> {
        val idList = mutableListOf<String>()
        for (BookFavEntity in database.getAllBoksFavs()) {
            idList.add(BookFavEntity.id_book)
        }
        return idList
    }

    fun deleteBookFromDatabase(idBook: String) {
        database.delete(BookFavEntity(idBook))
    }

    fun insertBookInDatabase(idBook: String) {
        database.insert(BookFavEntity(idBook))
    }

    suspend fun searchBookById(idBook: String): Response<Book> {
        return apiBook.searchLibro(idBook)
    }

    suspend fun searchBooksByName(nameBook : String) : Response<BookResponse>{
        return apiBook.getLibros(nameBook)
    }
    suspend fun searchBestSeller(nameList : String) : Response<BestSellerResponse>{
        return apiBestSeller.getBestSeller(nameList)
    }
    suspend fun searchArticleByName(nameArticle : String) : Response<ArticleResponse>{
        return apiArticle.getArticulos(nameArticle)
    }
}