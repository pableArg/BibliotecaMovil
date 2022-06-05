package com.example.bibliotecamovil.bibliotecamovil.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bibliotecamovil.R
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.Book
import com.example.bibliotecamovil.bibliotecamovil.ui.adapter.BookFavAdapter
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.FavViewModel
import com.example.bibliotecamovil.databinding.FragmentFavouriteBinding
import kotlin.reflect.KProperty


class FavouriteFragment : Fragment() {

    private lateinit var bookFavAdapter: BookFavAdapter
    private lateinit var favBinding: FragmentFavouriteBinding
    private val bookFavList = mutableListOf<Book>()
    private val model: FavViewModel by activityViewModels()

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
        bookFavAdapter = BookFavAdapter(bookFavList)
        favBinding.rv.adapter = bookFavAdapter
    }

    private fun setupObservers() {
        model.booksFavLiveData.observe(this, {
            favBinding.rv.adapter = BookFavAdapter(it)
            bookFavAdapter.notifyDataSetChanged()
        })
    }
    private fun getBooks() {
        if (model.getBooksFavIDList().isEmpty()) {
            /*EN CASO DE QUE ESTE VACIA LA LISTA QUE HACEMOS
            imgEmptyList.visibility = View.VISIBLE
            txtEmptyList.visibility = View.VISIBLE
            */
        }
        model.updateBooksLiveData(model.getBooksFavIDList())
        setupObservers()
    }
}

private operator fun Any.getValue(favouriteFragment: FavouriteFragment, property: KProperty<*>): FavViewModel {
}




