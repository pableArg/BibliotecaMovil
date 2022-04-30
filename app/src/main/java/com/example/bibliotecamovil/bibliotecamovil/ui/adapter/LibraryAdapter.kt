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


class LibraryAdapter(private val list: Array<String>) :
    RecyclerView.Adapter<LibraryAdapter.ViewHolder>() {
    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {


        val image: ImageView = item.findViewById(R.id.image)
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

        holder.title.text = "Titulo de ${list[position]}"
        holder.description.text = "Descripcion de ${list[position]}"

        holder.card.setOnClickListener {

            Toast.makeText(holder.context, "Este es el ${list[position]}", Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int = list.size
}