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
import com.example.bibliotecamovil.databinding.ItemCardBinding
import com.squareup.picasso.Picasso


class BookAdapter(var bookList: List<Book>) :

    RecyclerView.Adapter<BookViewHolder>() {
    private var listBook = mutableListOf<Book>()


    private lateinit var binding: ItemCardBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {

        val bookBinding =
            ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(bookBinding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = bookList[position]

        holder.binding.titleBook.text = book.libroInfo.titulo
        holder.binding.author.text = book.libroInfo.autores[0]
        val idLibro = book.id

        Picasso.get()
            .load("https://books.google.com/books/content?id=$idLibro&printsec=frontcover&img=1&zoom=1&source=gbs_api")
            .placeholder(R.drawable.notfound)
            .into(holder.binding.imageBook)


        holder.binding.image.setOnClickListener {
            val context: Context = holder.itemView.context
            Toast.makeText(
                context,
                "Este es el ${book.libroInfo.titulo}",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    override fun getItemCount(): Int = bookList.size

}

class BookViewHolder(val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root)

