package com.example.bibliotecamovil.bibliotecamovil.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.bibliotecamovil.R
import com.example.bibliotecamovil.bibliotecamovil.data.database.BookFavEntity
import com.example.bibliotecamovil.bibliotecamovil.data.database.LibraryEntity
import com.example.bibliotecamovil.bibliotecamovil.data.database.LibraryFavDatabase
import com.example.bibliotecamovil.bibliotecamovil.ui.adapter.BookStoreAdapter
import com.example.bibliotecamovil.databinding.FragmentBookstoresBinding

class BookstoresFragment : Fragment(){
    private lateinit var bookStoreAdapter : BookStoreAdapter
    private lateinit var bookstoresBinding: FragmentBookstoresBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookstores, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bookstoresBinding = FragmentBookstoresBinding.bind(view)

    }

    private fun initRecyclerView(){
        val database = context?.let {
            Room.databaseBuilder(
                it.applicationContext,LibraryFavDatabase::class.java, "library_database"
            ).build()
        }
        val book = BookFavEntity( "10", "1")
        val library = LibraryEntity("1", "libreria nueva")
        if (database != null) {
            database.libraryDao().insert(library)
        }
        bookstoresBinding.rv.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        bookStoreAdapter = database?.let { BookStoreAdapter(it) }!!

        bookstoresBinding.rv.adapter = bookStoreAdapter
    }

}