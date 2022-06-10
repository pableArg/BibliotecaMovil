package com.example.bibliotecamovil.bibliotecamovil.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bibliotecamovil.R
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.Book
import com.example.bibliotecamovil.bibliotecamovil.ui.adapter.BookAdapter
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.DetailViewModel
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.SearchViewModel
import com.example.bibliotecamovil.bibliotecamovil.utils.hideKeyboard
import com.example.bibliotecamovil.databinding.FragmentSearchBinding
import org.koin.android.compat.ScopeCompat.viewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.java.KoinJavaComponent.inject


class SearchFragment(private var bookAdapter: BookAdapter): Fragment() {
    private lateinit var searchBinding: FragmentSearchBinding
    private val bookList = mutableListOf<Book>()
    private val vmSearch : SearchViewModel by viewModel()



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
        setupObservers()
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
                        vmSearch.getBooks(this)
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
        vmSearch.getSearchedBooks().observe(viewLifecycleOwner) {
            bookAdapter.bookList = it
            bookAdapter.notifyDataSetChanged()
        }
    }
}

