package com.example.bibliotecamovil.bibliotecamovil.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bibliotecamovil.R
import com.example.bibliotecamovil.bibliotecamovil.data.database.LibraryFavDatabase
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.Book
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.BookAPIClient
import com.example.bibliotecamovil.bibliotecamovil.ui.adapter.BookAdapter
import com.example.bibliotecamovil.databinding.FragmentBookstoreBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FavouriteFragment : Fragment() {

    private lateinit var bookAdapter: BookAdapter
    private lateinit var favBinding: FragmentBookstoreBinding
    private val bookList = mutableListOf<Book>()
    private lateinit var database : LibraryFavDatabase
    val errorMessage = MutableLiveData<String>()
    //private val model: FavViewModel by activityViewModels() { FavViewModel.Factory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookstore, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favBinding = FragmentBookstoreBinding.bind(view)
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
        bookAdapter = BookAdapter(bookList)
        favBinding.rv.adapter = bookAdapter

    }

    private fun mostrarLibros(){

    val books = database.bookFavDao().getAllBoksFavs()

        for(book in books){
            CoroutineScope(Dispatchers.IO).launch {
            try {
                    val response = BookAPIClient().searchLibro(book.id_book)
                    if(response.isSuccessful && response.body() != null){
                        bookList.add(response.body()!!)
                    }
                    else{
                        val error = response.errorBody().toString()
                        errorMessage.value = error
                    }
                }
            catch (e : Exception) {
                errorMessage.value = e.message
            }
            }
            }

        }
    }


