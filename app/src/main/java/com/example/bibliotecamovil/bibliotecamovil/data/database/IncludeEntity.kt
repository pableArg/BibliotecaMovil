package com.example.bibliotecamovil.bibliotecamovil.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "include",
    primaryKeys = ["id_library", "id_book"]
    , foreignKeys = [ForeignKey(entity = LibraryEntity::class,
        parentColumns = arrayOf("id_library"),
        childColumns = arrayOf("id_library"),
        onDelete = ForeignKey.CASCADE
    ), ForeignKey(entity = BookFavEntity::class,
        parentColumns = arrayOf("id_book"),
        childColumns = arrayOf("id_book"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class IncludeEntity(
    @ColumnInfo(name = "id_library")
    val libraryID : String,
    @ColumnInfo(name = "id_book")
    val bookId : String
)
