package com.example.bibliotecamovil.bibliotecamovil.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bibliotecamovil.R
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.Book
import com.example.bibliotecamovil.bibliotecamovil.ui.adapter.BookAdapter
import com.example.bibliotecamovil.bibliotecamovil.ui.adapter.BookFavAdapter
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.FavViewModel
import com.example.bibliotecamovil.databinding.FragmentFavouriteBinding
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavouriteFragment(private var bookFavAdapter: BookFavAdapter) : Fragment() {

    //private lateinit var bookFavAdapter: BookFavAdapter
    private lateinit var favBinding: FragmentFavouriteBinding
    private val bookFavList = mutableListOf<Book>()
    private val vmFav: FavViewModel by viewModel()

    //private val bookFavAdapter : BookFavAdapter by inject()
    //private val bookFavAdapter : BookFavAdapter = get()
    val search: SearchFragment by inject()


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
        getBooks()
    }

    private fun initRecyclerView() {
        favBinding.rv.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        favBinding.rv.adapter = bookFavAdapter
    }


    private fun setupObservers() {
        vmFav.getFavBooks().observe(viewLifecycleOwner) {
            bookFavAdapter.bookFavList = it
            bookFavAdapter.notifyDataSetChanged()
        }
    }

    private fun getBooks() {
        if (vmFav.getBooksFavIDList().isEmpty()) {
            /*EN CASO DE QUE ESTE VACIA LA LISTA QUE HACEMOS
            imgEmptyList.visibility = View.VISIBLE
            txtEmptyList.visibility = View.VISIBLE
            */
        }
        vmFav.updateBooksLiveData(vmFav.getBooksFavIDList())
        setupObservers()
    }
}






