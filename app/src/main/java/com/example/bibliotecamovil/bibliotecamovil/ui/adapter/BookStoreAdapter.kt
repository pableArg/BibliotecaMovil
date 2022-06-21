package com.example.bibliotecamovil.bibliotecamovil.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.bibliotecamovil.bibliotecamovil.data.database.BookFavDAO
import com.example.bibliotecamovil.bibliotecamovil.data.database.LibraryDAO
import com.example.bibliotecamovil.bibliotecamovil.data.database.LibraryFavDatabase
import com.example.bibliotecamovil.databinding.ItemCardBookstoreBinding

class BookStoreAdapter(val database: LibraryFavDatabase)
    : RecyclerView.Adapter<BookStoreViewHolder>() {

    val librariesList = database.libraryDao().getLibraries()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookStoreViewHolder {
        val bookstoreBinding = ItemCardBookstoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookStoreViewHolder(bookstoreBinding)
    }

    override fun onBindViewHolder(holder: BookStoreViewHolder, position: Int) {
    val librarie = librariesList[position]

        holder.binding.tittleBookStore.text = librarie.name
    }

    override fun getItemCount(): Int = librariesList.size
}



class BookStoreViewHolder(val binding : ItemCardBookstoreBinding) : RecyclerView.ViewHolder(binding.root)