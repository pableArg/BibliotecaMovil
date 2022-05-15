package com.example.bibliotecamovil.bibliotecamovil.ui.fragments

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuItemCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bibliotecamovil.R
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.Book
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.BookByAPI
import com.example.bibliotecamovil.bibliotecamovil.ui.adapter.BookAdapter
import com.example.bibliotecamovil.databinding.FragmentSearchBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


 class SearchFragment : Fragment() {
    private lateinit var bookAdapter: BookAdapter
    private lateinit var searchBinding: FragmentSearchBinding
    private val bookList = mutableListOf<Book>()
    private lateinit var menuItem : MenuItem
    private lateinit var searchView : SearchView
    private lateinit var toolbar : Toolbar


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view : View = inflater.inflate(R.layout.fragment_search, container, false)

        toolbar = view.findViewById(R.id.barra)
        var activity : AppCompatActivity = context as AppCompatActivity
        activity.setSupportActionBar(toolbar)
        activity.supportActionBar?.setTitle("")

        return view

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.options_menu, menu)
        menuItem = menu.findItem(R.id.search)
        searchView = MenuItemCompat.getActionView(menuItem) as SearchView
        searchView.setIconifiedByDefault(true)

        var searchManager: SearchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))

        searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.run {

                        search(query)
                    }
                    return true
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            }
        )
        super.onCreateOptionsMenu(menu, inflater)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        searchBinding = FragmentSearchBinding.bind(view)
        initRecyclerView()

    }


    private fun initRecyclerView() {
       searchBinding.rv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        bookAdapter = BookAdapter(bookList)
        searchBinding.rv.adapter = bookAdapter

    }
    private fun search(query : String){
        CoroutineScope(Dispatchers.IO).launch {
            val response = BookByAPI().getLibros(query)

            if (response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main){
                    bookAdapter.bookList = response.body()!!.items
                    bookAdapter.notifyDataSetChanged()

                }
            }
            else{
            }
        }
    }

/*
    private fun setSearchViewListener() {
        searchBinding.sv.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.run {

                        model.getBooks(query)
                    }
                    return true
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
    }
*/
    /*private fun setupObservers(){
        model. .observe(this, Observer {
            moviesAdapter.updateMovies(it.results)
            moviesAdapter.notifyDataSetChanged()
        })
    }*/
}

