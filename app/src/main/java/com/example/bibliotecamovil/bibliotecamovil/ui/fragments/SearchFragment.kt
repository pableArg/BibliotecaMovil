package com.example.bibliotecamovil.bibliotecamovil.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bibliotecamovil.R
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.Book
import com.example.bibliotecamovil.bibliotecamovil.ui.adapter.BookAdapter
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.SearchViewModel
import com.example.bibliotecamovil.bibliotecamovil.utils.hideKeyboard
import com.example.bibliotecamovil.databinding.FragmentSearchBinding


class SearchFragment() : Fragment() {
    private lateinit var searchBinding: FragmentSearchBinding
    private lateinit var bookAdapter: BookAdapter
    private val bookList = mutableListOf<Book>()
    private val model: SearchViewModel by activityViewModels() { SearchViewModel.Factory() }
    private val randomBooks = "s1gVAAAAYAAJ"
    private lateinit var llContenedor: LinearLayout
    private lateinit var llCargando: LinearLayout


    //private val detailViewModel by sharedViewModel<DetailViewModel>()
    // private val model by sharedViewModel<SearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        searchBinding = FragmentSearchBinding.bind(view)
        getRandomBooks(randomBooks)
        setSearchViewListener()
        initRecyclerView()
        setupObservers()

      /*  llContenedor = searchBinding.llContenedor
        llCargando = searchBinding.llCargando
        Handler(Looper.getMainLooper()).postDelayed({
            llCargando.isVisible = false
            llContenedor.isVisible = true

        }, 3000)
            llCargando.isVisible=true*/
    }

    private fun initRecyclerView() {
        searchBinding.rv.layoutManager = GridLayoutManager(this.context, 2)
        bookAdapter = BookAdapter(bookList)
        searchBinding.rv.adapter = bookAdapter
    }

    private fun setSearchViewListener() {
        searchBinding.sv.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.run {
                        model.retrieveBooks(this)
                    }
                    hideKeyboard()
                    return true
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
    }

    private fun getRandomBooks(randomBooks: String) {
        model.retrieveBooks(randomBooks)
    }

    private fun setupObservers() {
        model.getBooks().observe(viewLifecycleOwner) {
            bookAdapter.bookList = it
            bookAdapter.notifyDataSetChanged()
        }
    }
}
