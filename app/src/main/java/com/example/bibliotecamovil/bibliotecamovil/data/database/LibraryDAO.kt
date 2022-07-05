package com.example.bibliotecamovil.bibliotecamovil.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LibraryDAO {

    @Query("SELECT * FROM libraries")
    fun getLibraries() : MutableList<LibraryEntity>

    @Query("SELECT fv.id_book FROM libraries l join include i on l.id_library = i.id_library "+
            "join favs_books fv on i.id_book = fv.id_book " +
            "where i.id_library = :idLibrary"
    )
    fun getBooksByLibrary(idLibrary : String) : MutableList<BookFavEntity>

    @Insert
    fun insert(library : LibraryEntity)

    @Delete
    fun delete(library : LibraryEntity)
}