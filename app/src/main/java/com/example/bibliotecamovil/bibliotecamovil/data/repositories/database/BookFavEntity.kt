package com.example.bibliotecamovil.bibliotecamovil.data.repositories.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "favs_books")
data class BookFavEntity (
    @PrimaryKey
    @ColumnInfo(name = "id_book")
    val id_book: Int
)