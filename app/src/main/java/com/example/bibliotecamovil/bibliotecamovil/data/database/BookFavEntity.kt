package com.example.bibliotecamovil.bibliotecamovil.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity (tableName = "favs_books")

data class BookFavEntity(
    @PrimaryKey
    @ColumnInfo(name = "id_book")
    val idBook: String
)