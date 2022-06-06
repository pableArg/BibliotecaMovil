package com.example.bibliotecamovil.bibliotecamovil.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LibraryDAO {

    @Query("SELECT * FROM libraries")
    fun getLibraries() : List<LibraryEntity>

    @Insert
    fun insert(library : LibraryEntity)

    @Delete
    fun delete(library : LibraryEntity)
}