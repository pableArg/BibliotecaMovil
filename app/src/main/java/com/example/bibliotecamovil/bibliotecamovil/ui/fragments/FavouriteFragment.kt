package com.example.bibliotecamovil.bibliotecamovil.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bibliotecamovil.R
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retofit.Book
import com.example.bibliotecamovil.bibliotecamovil.ui.adapter.BookAdapter
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.DetailViewModel
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.FavViewModel
import com.example.bibliotecamovil.databinding.FragmentFavouriteBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class FavouriteFragment : Fragment() {

    private lateinit var adapter: BookAdapter
    private lateinit var favBinding: FragmentFavouriteBinding
    private val favModel by sharedViewModel<FavViewModel>()
    private val list = mutableListOf<Book>()
    private val detailViewModel by sharedViewModel<DetailViewModel>()

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

    @SuppressLint("NotifyDataSetChanged")
    private fun setupObservers() {
        favModel.booksFavLiveData.observe(viewLifecycleOwner) {
            adapter.bookList = it
            adapter.notifyDataSetChanged()
        }
    }

    private fun initRecyclerView() {
        favBinding.rv.layoutManager =
            GridLayoutManager(activity, 2)
        adapter = BookAdapter(list, requireActivity(), detailViewModel) { view ->
            view.findNavController()
                .navigate(FavouriteFragmentDirections.actionFavouriteFragmentToDetailFragment())
        }
        favBinding.rv.adapter = adapter
    }
}