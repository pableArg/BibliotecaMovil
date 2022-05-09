package com.example.bibliotecamovil.bibliotecamovil.ui.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bibliotecamovil.R
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.Book
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.BookByAPI
import com.example.bibliotecamovil.bibliotecamovil.ui.adapter.BookAdapter
import com.example.bibliotecamovil.databinding.FragmentRecyclerCardsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RecyclerCardsFragment : Fragment(), androidx.appcompat.widget.SearchView.OnQueryTextListener{

    private lateinit var binding: FragmentRecyclerCardsBinding
    private val libros = mutableListOf<Book>()
    private var adapter = BookAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = setContentView(
            this.context as Activity,
            R.layout.fragment_recycler_cards
        )

        return inflater.inflate(R.layout.fragment_recycler_cards, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        binding.sv.setOnQueryTextListener(this)
    }


    private fun initRecyclerView(){
        adapter = BookAdapter()
        binding.rv.layoutManager = LinearLayoutManager(activity)
        binding.rv.adapter = adapter
    }



    private fun searchByName(query : String,) {
        CoroutineScope(Dispatchers.IO).launch {

            val response = BookByAPI().getLibros(query)

            if (response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main){

                    adapter.updateBooks(response.body()?.items ?: emptyList())
                    adapter.notifyDataSetChanged()

                }
            }
            else{
                showError()
            }
        }

    }

    private fun showError() {

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()) {
            searchByName(query.toLowerCase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}






