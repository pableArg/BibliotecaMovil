package com.example.bibliotecamovil.bibliotecamovil.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.findNavController
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import com.example.bibliotecamovil.bibliotecamovil.ui.fragments.SearchFragmentDirections
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.DetailViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.bibliotecamovil.R
import com.example.bibliotecamovil.bibliotecamovil.data.database.BookFavEntity
import com.example.bibliotecamovil.bibliotecamovil.data.database.LibraryFavDatabase
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.Book
import com.example.bibliotecamovil.bibliotecamovil.domain.model.BookResponse
import com.example.bibliotecamovil.databinding.ItemCardBinding
import com.squareup.picasso.Picasso
import org.koin.androidx.compose.inject
import org.koin.java.KoinJavaComponent.inject



class BookAdapter(var bookList: MutableList<Book>) :

    RecyclerView.Adapter<BookViewHolder>() {

    private lateinit var databse: LibraryFavDatabase

    private val detailViewModel: DetailViewModel by inject(DetailViewModel::class.java)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {

        val bookBinding =
            ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(bookBinding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = bookList[position]

        try {
        holder.binding.titleBook.text = book.libroInfo.titulo
        } catch (e : Exception){
        holder.binding.titleBook.text = "Titulo no disponible"
        }

        try {
            holder.binding.author.text = book.libroInfo.autores[0]
        } catch (e : Exception) {
            holder.binding.author.text = "Autor no disponible"
        }

        val idLibro = book.id

        Picasso.get()
            .load("https://books.google.com/books/content?id=$idLibro&printsec=frontcover&img=1&zoom=1&source=gbs_api")
            .placeholder(R.drawable.notfound)
            .into(holder.binding.imageBook)


        holder.binding.imageBook.setOnClickListener {
            val context: Context = holder.itemView.context
            Toast.makeText(
                context,
                "Este es el ${book.libroInfo.titulo}",
                Toast.LENGTH_SHORT
            ).show()
        }
/*
        holder.binding.cv.setOnClickListener{view ->
            val searchFragmentDirections = SearchFragmentDirections.actionSearchFragmentToInfoFragment(idLibro)
            Navigation.findNavController(view).navigate(searchFragmentDirections)
        }
        */


        holder.binding.favouriteBook.setOnClickListener {
            holder.binding.favouriteBook.visibility = View.GONE
            databse.bookFavDao().insert(BookFavEntity(idLibro))
        }
    }

    override fun getItemCount(): Int = bookList.size

}

class BookViewHolder(val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root)

