package com.example.bibliotecamovil.bibliotecamovil.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bibliotecamovil.bibliotecamovil.data.database.LibraryEntity
import com.example.bibliotecamovil.databinding.ItemCardLibraryBinding

class LibraryAdapter(var librariesList: MutableList<LibraryEntity>)
    : RecyclerView.Adapter<LibraryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryViewHolder {
        val libraryBinding = ItemCardLibraryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LibraryViewHolder(libraryBinding)
    }

    override fun onBindViewHolder(holder: LibraryViewHolder, position: Int) {
        val librarie = librariesList[position]

        holder.binding.tittleBookStore.text = librarie.name
    }

    override fun getItemCount(): Int = librariesList.size
}



class LibraryViewHolder(val binding : ItemCardLibraryBinding) : RecyclerView.ViewHolder(binding.root)