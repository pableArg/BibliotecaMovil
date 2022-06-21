package com.example.bibliotecamovil.bibliotecamovil.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LibraryDAO {

    @Query("SELECT * FROM libraries")
    fun getLibraries() : List<LibraryEntity>

    @Query("SELECT B.id_book FROM libraries L JOIN favs_books B ON L.id = B.libraryId")
    fun getBookByLibrary() : List<BookFavEntity>

    @Insert
    fun insert(library : LibraryEntity)

    @Delete
    fun delete(library : LibraryEntity)
}