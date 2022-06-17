package com.example.bibliotecamovil.bibliotecamovil.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bibliotecamovil.R
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.BestSellerAPIClient
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.Book
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.BookAPIClient
import com.example.bibliotecamovil.bibliotecamovil.ui.adapter.BookAdapter
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.SearchViewModel
import com.example.bibliotecamovil.bibliotecamovil.utils.hideKeyboard
import com.example.bibliotecamovil.databinding.FragmentSearchBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception


class SearchFragment : Fragment() {
    private lateinit var bookAdapter: BookAdapter
    private lateinit var searchBinding: FragmentSearchBinding
    private val bookList = mutableListOf<Book>()
    private val modal : StartBookShelfFragment = StartBookShelfFragment()
    private val model: SearchViewModel by activityViewModels() { SearchViewModel.Factory() }

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

        setSearchViewListener()
        initRecyclerView()
        model.setBooks()
        setupObservers()

        searchBinding.add.setOnClickListener {
            modal.show(parentFragmentManager, "hola")
        }
    }


    private fun initRecyclerView() {
        searchBinding.rv.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        bookAdapter = BookAdapter(bookList)
        searchBinding.rv.adapter = bookAdapter

    }


    private fun setSearchViewListener() {
        searchBinding.sv.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.run {
                        searchBinding.textSearch.visibility = View.GONE
                        model.getBooks(this)
                    }
                    hideKeyboard()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
    }


    private fun setupObservers() {

        model.getSearchedBooks().observe(viewLifecycleOwner) {
            bookAdapter.bookList = it
            bookAdapter.notifyDataSetChanged()
        }
    }
/*
    private fun setLibros(){
         CoroutineScope(Dispatchers.Main).launch{
                val response = BestSellerAPIClient().getBestSeller("hardcover-fiction")
                if (response.isSuccessful && response.body() != null) {
                    val books = response.body()!!

                    if(books.results.libros != null) {
                        for(book in books.results.libros) {

                            BookAPIClient().getLibros(book.titulo).body()?.items?.get(1)
                                ?.let { bookAdapter.bookList.add(it) }
                        }
                        bookAdapter.notifyDataSetChanged()
                    }
                }

        }
    }
    */

}

