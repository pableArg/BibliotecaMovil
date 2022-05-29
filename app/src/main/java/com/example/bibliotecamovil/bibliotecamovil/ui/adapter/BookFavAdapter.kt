package com.example.bibliotecamovil.bibliotecamovil.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.bibliotecamovil.R
import com.example.bibliotecamovil.bibliotecamovil.data.database.LibraryFavDatabase
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.database.BookFavEntity
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.Book
import com.example.bibliotecamovil.databinding.ItemCardBinding
import com.example.bibliotecamovil.databinding.ItemCardFavBinding
import com.squareup.picasso.Picasso



class BookFavAdapter(val bookList: List<Book>) :
    RecyclerView.Adapter<BookFavViewHolder>() {

    private lateinit var binding: ItemCardFavBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookFavViewHolder {

        val bookBinding =
            ItemCardFavBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookFavViewHolder(bookBinding)

    }

    override fun onBindViewHolder(holder: BookFavViewHolder, position: Int) {
        val book = bookList[position]

        holder.binding.titleBook.text = book.volumeInfo.title
        holder.binding.author.text = book.volumeInfo.authors[0]
        val idLibro = book.id
        Picasso.get()
            .load("https://books.google.com/books/content?id=$idLibro&printsec=frontcover&img=1&zoom=1&source=gbs_api")
            .placeholder(R.drawable.notfound)
            .into(holder.binding.imageBook)




        /*
        holder.binding.deleteBook.setOnClickListener{

            //delete from database
            // mantener position al eliminar item
        }
*/


    }

    override fun getItemCount(): Int = bookList.size


}

class BookFavViewHolder(val binding: ItemCardFavBinding) : RecyclerView.ViewHolder(binding.root)