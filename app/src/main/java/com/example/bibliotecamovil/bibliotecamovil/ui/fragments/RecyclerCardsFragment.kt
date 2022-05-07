package com.example.bibliotecamovil.bibliotecamovil.ui.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bibliotecamovil.R
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.Book
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.BookByAPI
import com.example.bibliotecamovil.bibliotecamovil.domain.model.BookResponse
import com.example.bibliotecamovil.bibliotecamovil.ui.adapter.BookAdapter
import com.example.bibliotecamovil.databinding.FragmentRecyclerCardsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RecyclerCardsFragment : Fragment(){

    private lateinit var binding: FragmentRecyclerCardsBinding
    private lateinit var rv : RecyclerView
    private val libros = mutableListOf<Book>()
    private var adapter = BookAdapter(libros)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = setContentView(
            this.context as Activity,
            R.layout.fragment_recycler_cards
        )


        rv = binding.rv
        searchByName("Rayuela")
        rv.layoutManager = LinearLayoutManager(activity)

        binding.sv.setOnQueryTextListener( object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextChange(nextText: String?): Boolean {

                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    searchByName(query.toLowerCase())
                }
                return true
            }

        })

        return inflater.inflate(R.layout.fragment_recycler_cards, container, false)
    }


    private fun searchByName(query : String,) {
        CoroutineScope(Dispatchers.IO).launch {
            BookByAPI().getLibros(query, object : Callback<BookResponse> {

                override fun onFailure(call: Call<BookResponse>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<BookResponse>,
                    response: Response<BookResponse>
                ) {
                    if (response.isSuccessful) {

                        val received = response.body()
                        if (received != null) {

                            libros.clear()
                            libros.addAll(received.items)
                            adapter.bookList = libros
                            adapter.notifyDataSetChanged()
                            rv.adapter = adapter
                        }

                    }
                }
            })
        }
    }

    }



