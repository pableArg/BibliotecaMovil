package com.example.bibliotecamovil.bibliotecamovil.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bibliotecamovil.R
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.Book
import com.example.bibliotecamovil.bibliotecamovil.ui.adapter.BookAdapter
import com.example.bibliotecamovil.bibliotecamovil.ui.adapter.BookFavAdapter
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.SearchViewModel
import com.example.bibliotecamovil.databinding.FragmentFavouriteBinding
import com.example.bibliotecamovil.databinding.FragmentSearchBinding


class FavouriteFragment : Fragment() {

    private lateinit var bookFavAdapter: BookFavAdapter
    private lateinit var favBinding: FragmentFavouriteBinding
    private val bookFavList = mutableListOf<Book>()

    //private val model: FavViewModel by activityViewModels() { FavViewModel.Factory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favBinding = FragmentFavouriteBinding.bind(view)
        initRecyclerView()
        setupObservers()
    }

    private fun setupObservers() {
       /* model.getFavBooks().observe(viewLifecycleOwner) {
            bookFavAdapter.bookFavList = it
            bookFavAdapter.notifyDataSetChanged()
        }*/
    }

    private fun initRecyclerView() {
        favBinding.rv.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        bookFavAdapter = BookFavAdapter(bookFavList)
        favBinding.rv.adapter = bookFavAdapter
    }


}