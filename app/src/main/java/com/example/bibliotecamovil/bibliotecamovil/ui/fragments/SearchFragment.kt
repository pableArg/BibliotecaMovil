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
import com.example.bibliotecamovil.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {
    private lateinit var bookAdapter: BookAdapter
    private lateinit var searchBinding: FragmentSearchBinding
    private val bookList = mutableListOf<Book>()
    //private val vm: SearchViewModel by viewModel()


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
        initRecyclerView()
        //setSearchViewListener()


    }

    private fun initRecyclerView() {
       searchBinding.rv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        bookAdapter = BookAdapter(bookList)
        searchBinding.rv.adapter = bookAdapter

    }

   /* private fun setSearchViewListener() {
        searchBinding.sv.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.run {
                        vm.getBook  (this)
                    }
                    return true
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
    }*/
}

