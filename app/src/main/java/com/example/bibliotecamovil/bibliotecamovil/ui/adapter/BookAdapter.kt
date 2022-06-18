package com.example.bibliotecamovil.bibliotecamovil.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.bibliotecamovil.R
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.Book
import com.example.bibliotecamovil.bibliotecamovil.ui.fragments.SearchFragment
import com.example.bibliotecamovil.bibliotecamovil.ui.fragments.SearchFragmentDirections
import com.example.bibliotecamovil.databinding.ItemCardBinding
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso


class BookAdapter(
    var bookList: MutableList<Book>,
    var context: Context
) :

    RecyclerView.Adapter<BookViewHolder>() {
    val errorMessage = MutableLiveData<String>()


    //private val detailViewModel: DetailViewModel by viewModels()
    //private lateinit var databse: LibraryFavDatabase
    // private val detailViewModel: DetailViewModel by inject(DetailViewModel::class.java)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {

        val bookBinding =
            ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(bookBinding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = bookList[position]
        holder.binding.titleBook.text = book.libroInfo.titulo
        val idLibro = book.id
        try {
            Picasso.get()
                .load("https://books.google.com/books/content?id=$idLibro&printsec=frontcover&img=1&zoom=1&source=gbs_api")
                .placeholder(R.drawable.notfound)
                .into(holder.binding.imageBook)
            holder.binding.imageBook.setOnClickListener { fabView ->
                //detailViewModel.bookDetail.value = book
                fabView.findNavController().navigate(
                    SearchFragmentDirections
                        .actionSearchFragmentToDetailFragment()
                )
            }
        } catch (e: Exception) {
            Toast.makeText(context, "No se puede abrir el detalle", Toast.LENGTH_LONG).show()
            Firebase.crashlytics.recordException(e)
        }
    }

    override fun getItemCount(): Int = bookList.size

}

class BookViewHolder(val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root)

