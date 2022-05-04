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
import com.squareup.picasso.Picasso


class LibraryAdapter() :
    RecyclerView.Adapter<LibraryAdapter.ViewHolder>() {

    var list = BookResponse(ArrayList<Book>())
    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {


        val image: ImageView = item.findViewById(R.id.image_book)
        val title: TextView = item.findViewById(R.id.book_title)
        val description: TextView = item.findViewById(R.id.book_description)
        val card: CardView = item.findViewById(R.id.cv)
        val context: Context = item.context


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)

        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.title.text = list.items[position].volumeInfo.title
        holder.description.text = list.items[position].volumeInfo.authors[0]
        val idLibro = list.items[position].id

            Picasso.get()
                .load("https://books.google.com/books/content?id=$idLibro&printsec=frontcover&img=1&zoom=1&source=gbs_api")
                .into(holder.image)

        holder.card.setOnClickListener {

            Toast.makeText(holder.context, "Este es el ${list.items[position].volumeInfo.title}", Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int = list.items.size

}

