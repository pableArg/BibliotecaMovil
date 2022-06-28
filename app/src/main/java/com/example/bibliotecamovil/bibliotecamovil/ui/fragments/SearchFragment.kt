package com.example.bibliotecamovil.bibliotecamovil.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bibliotecamovil.R
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retofit.Book
import com.example.bibliotecamovil.bibliotecamovil.ui.adapter.BookAdapter
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.DetailViewModel
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.SearchViewModel
import com.example.bibliotecamovil.bibliotecamovil.utils.hideKeyboard
import com.example.bibliotecamovil.databinding.FragmentSearchBinding
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SearchFragment() : Fragment() {
    private lateinit var searchBinding: FragmentSearchBinding
    private lateinit var bookAdapter: BookAdapter
    private val bookList = mutableListOf<Book>()


    private val model by sharedViewModel<SearchViewModel>()
    private val detailViewModel by sharedViewModel<DetailViewModel>()

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
        setBooks()
    }

    private fun initRecyclerView() {
        searchBinding.rv.layoutManager = GridLayoutManager(this.context, 2)
        bookAdapter = BookAdapter(
            bookList, requireActivity(), detailViewModel
        ) { view ->
            view.findNavController()
                .navigate(SearchFragmentDirections.actionSearchFragmentToDetailFragment2())
        }
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
                        searchBinding.rv.visibility = View.GONE
                        searchBinding.progressSearch.visibility = View.VISIBLE
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

    private fun setBooks() {
        if (model.getSearchedBooks().value == null) {
            searchBinding.query.visibility = View.VISIBLE
            model.setBooks()
            setupObservers()
        } else {
            setupObservers()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupObservers() {
        model.getSearchedBooks().observe(viewLifecycleOwner) {
            bookAdapter.bookList = it
            bookAdapter.notifyDataSetChanged()
            searchBinding.rv.visibility = View.VISIBLE
            searchBinding.progressSearch.visibility = View.GONE
        }
    }
}

