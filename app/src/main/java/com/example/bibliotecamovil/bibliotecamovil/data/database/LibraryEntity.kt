package com.example.bibliotecamovil.bibliotecamovil.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "libraries")

data class LibraryEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id : String,
    @ColumnInfo(name = "name")
    val name: String
)
