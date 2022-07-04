package com.example.bibliotecamovil.bibliotecamovil.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bibliotecamovil.bibliotecamovil.data.database.BookFavEntity


@Database(
    version = 1,
    entities = [BookFavEntity::class, LibraryEntity::class]
)
abstract class LibraryFavDatabase : RoomDatabase() {
    abstract fun bookFavDao(): BookFavDAO
    abstract fun libraryDao():LibraryDAO

    companion object {
        private var INSTANCE: LibraryFavDatabase? = null
        fun getDatabase(context: Context): LibraryFavDatabase {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                LibraryFavDatabase::class.java,
                "cards"
            ).build()
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}