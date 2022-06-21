package com.example.bibliotecamovil.bibliotecamovil.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity (tableName = "favs_books"
    , foreignKeys = arrayOf(
        ForeignKey(entity = LibraryEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("libraryId"),
            onDelete = ForeignKey.CASCADE
        )
    ))
data class BookFavEntity(
    @PrimaryKey
    @ColumnInfo(name = "id_book")
    val id_book: String,
    @ColumnInfo(name = "libraryId")
    var libraryId : String

)