package com.example.bibliotecamovil.bibliotecamovil.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bibliotecamovil.R
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.Book
import com.example.bibliotecamovil.bibliotecamovil.ui.adapter.BookAdapter
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.DetailViewModel
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.SearchViewModel
import com.example.bibliotecamovil.bibliotecamovil.utils.hideKeyboard
import com.example.bibliotecamovil.databinding.FragmentSearchBinding
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class SearchFragment() : Fragment() {
    private lateinit var searchBinding: FragmentSearchBinding
    private lateinit var bookAdapter: BookAdapter
    private val bookList = mutableListOf<Book>()
    //private val model: SearchViewModel by activityViewModels() { SearchViewModel.Factory() }
    private val randomBooks = "s1gVAAAAYAAJ"
    private lateinit var llContenedor: LinearLayout
    private lateinit var llCargando: LinearLayout

    //private val detailViewModel by sharedViewModel<DetailViewModel>()
    private val model by sharedViewModel<SearchViewModel>()

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
    }

    private fun initRecyclerView() {
        searchBinding.rv.layoutManager = GridLayoutManager(this.context, 2)
        //bookAdapter = BookAdapter(bookList, requireActivity())
        //bookAdapter.bookList = bookList
        searchBinding.rv.adapter = bookAdapter

    }

    private fun setSearchViewListener() {
        try {
            searchBinding.sv.setOnQueryTextListener(
                object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        query?.run {
                            model.getBooks(this)
                        }
                        hideKeyboard()
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        return false
                    }
                })

        } catch (e: Exception) {
            Firebase.crashlytics.recordException(e)
        }

    }

    private fun getRandomBooks(randomBooks: String) {
        try {
            model.getBooks(randomBooks)
        } catch (e: Exception) {
            Toast.makeText(context, "No se pudo recuperar los libros", Toast.LENGTH_LONG).show()
        }
    }


    private fun setupObservers() {
        model.getSearchedBooks().observe(viewLifecycleOwner) {
            bookAdapter.bookList = it
            bookAdapter.notifyDataSetChanged()
        }
    }
}

