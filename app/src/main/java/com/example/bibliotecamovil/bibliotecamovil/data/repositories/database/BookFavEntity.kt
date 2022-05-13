package com.example.bibliotecamovil.bibliotecamovil.data.repositories.database

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity (tableName = "favs_books")
data class BookFavEntity (
    @ColumnInfo(name = "id_book")
    val id_book: Int
)