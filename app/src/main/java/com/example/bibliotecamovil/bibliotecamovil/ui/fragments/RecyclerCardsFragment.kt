package com.example.bibliotecamovil.bibliotecamovil.ui.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
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


class RecyclerCardsFragment : Fragment(){

    private lateinit var binding: FragmentRecyclerCardsBinding
    private val libros = mutableListOf<Book>()
    private var adapter = BookAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
        setupSearchView(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
            requireView().findNavController())

    }

    private fun setupSearchView(menu : Menu){
    val searchItem : MenuItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                if(!query.isNullOrEmpty()) {
                    searchByName(query.toLowerCase())
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
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

}






