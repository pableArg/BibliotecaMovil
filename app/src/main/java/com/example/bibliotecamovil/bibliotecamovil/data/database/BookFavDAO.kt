package com.example.bibliotecamovil.bibliotecamovil.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookFavDAO {
    @Query ("SELECT * FROM favs_books")
    fun getAllBoksFavs(): List<BookFavEntity>

    @Query("SELECT * FROM favs_books WHERE favs_books.id_book = :bookBuscado")
    fun getBooksById (bookBuscado: Int): BookFavEntity
    @Delete
    fun delete(entity: BookFavEntity)
    @Insert
    fun insert(entity: BookFavEntity)

}