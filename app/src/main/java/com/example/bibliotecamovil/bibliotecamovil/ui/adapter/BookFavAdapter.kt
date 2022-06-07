package com.example.bibliotecamovil.bibliotecamovil.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bibliotecamovil.R
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.Book
import com.example.bibliotecamovil.bibliotecamovil.domain.model.VerificarEnFavoritos
import com.example.bibliotecamovil.databinding.ItemCardFavBinding
import com.squareup.picasso.Picasso



class BookFavAdapter(var bookFavList: List<Book>, private val validacion: VerificarEnFavoritos) :
    RecyclerView.Adapter<BookFavViewHolder>() {

    private lateinit var binding: ItemCardFavBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookFavViewHolder {

        val bookBinding =
            ItemCardFavBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookFavViewHolder(bookBinding)

    }

    override fun onBindViewHolder(holder: BookFavViewHolder, position: Int) {
        val book = bookFavList[position]

        holder.binding.titleBook.text = book.libroInfo.titulo
        holder.binding.author.text = book.libroInfo.autores[0]
        val idLibro = book.id
        Picasso.get()
            .load("https://books.google.com/books/content?id=$idLibro&printsec=frontcover&img=1&zoom=1&source=gbs_api")
            .placeholder(R.drawable.notfound)
            .into(holder.binding.imageBook)
        if(validacion.intoFavs(book.id)) {
            holder.binding.deleteBook// seteo image si esta
        }else{
            holder.binding.deleteBook // seteo image no esta
        }
        holder.binding.deleteBook.setOnClickListener{
            validacion.addOrDeleteNewMovieFav(book.id)
        }
*/


    }

    override fun getItemCount(): Int = bookFavList.size


}

class BookFavViewHolder(val binding: ItemCardFavBinding) : RecyclerView.ViewHolder(binding.root)