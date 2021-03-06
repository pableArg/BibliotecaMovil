package com.example.bibliotecamovil.bibliotecamovil.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.bibliotecamovil.R
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.Book
import com.example.bibliotecamovil.bibliotecamovil.ui.fragments.SearchFragment
import com.example.bibliotecamovil.bibliotecamovil.ui.fragments.SearchFragmentDirections
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.DetailViewModel
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.SearchViewModel
import com.example.bibliotecamovil.databinding.ItemCardBinding
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import org.koin.androidx.compose.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class BookAdapter(
    var bookList: MutableList<Book>,
    private val context: Context,
    private val detailModel: DetailViewModel,
    private val onClickListener: View.OnClickListener
) :

    RecyclerView.Adapter<BookViewHolder>() {
    //    lateinit var context: Context


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
            holder.binding.cardView.setOnClickListener { view ->
                detailModel.bookDetail.value = book
                /* .navigate(
                SearchFragmentDirections
                    .actionSearchFragmentToDetailFragment()
            )*/
                onClickListener.onClick(view);
                //findNavController(view).navigate(SearchFragmentDirections.actionSearchFragmentToDetailFragment())

            }
        } catch (e: Exception) {
            Toast.makeText(context, "No se puede abrir el detalle", Toast.LENGTH_LONG).show()
            Firebase.crashlytics.recordException(e)
        }
    }

    override fun getItemCount(): Int = bookList.size

}

class BookViewHolder(val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root)

interface onClick {

}

