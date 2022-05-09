package com.example.bibliotecamovil.bibliotecamovil.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.bibliotecamovil.R
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.Book
import com.example.bibliotecamovil.bibliotecamovil.domain.model.BookResponse
import com.example.bibliotecamovil.databinding.FragmentRecyclerCardsBinding
import com.example.bibliotecamovil.databinding.ItemCardBinding
import com.squareup.picasso.Picasso


class BookAdapter () :  RecyclerView.Adapter<BookViewHolder>() {

    private val bookList = mutableListOf<Book>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {

        val layout = ItemCardBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return BookViewHolder(layout)
    }

    override fun onBindViewHolder(holderBook: BookViewHolder, position: Int) {
        val movies = bookList[position]
        holderBook.binding.bookTitle.text = movies.volumeInfo.title
        holderBook.binding.bookAuthor.text = movies.volumeInfo.authors[0]

        val idBook = bookList[position].id

        Picasso.get()
            .load("https://books.google.com/books/content?id=$idBook&printsec=frontcover&img=1&zoom=1&source=gbs_api%22")
                .into(holderBook.binding.imageBook)

    }
    override fun getItemCount(): Int = bookList.size

    fun updateBooks(results : List<Book>?){
        bookList.clear()
        if(results != null){
            bookList.addAll(results)
        }
    }
}

class BookViewHolder(val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root)



