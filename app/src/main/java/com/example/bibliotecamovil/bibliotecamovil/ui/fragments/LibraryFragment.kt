package com.example.bibliotecamovil.bibliotecamovil.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bibliotecamovil.R
import com.example.bibliotecamovil.bibliotecamovil.data.database.LibraryEntity
import com.example.bibliotecamovil.bibliotecamovil.ui.adapter.LibraryAdapter
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.LibraryViewModel
import com.example.bibliotecamovil.databinding.FragmentLibraryBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class LibraryFragment : Fragment() {

    private lateinit var libraryAdapter : LibraryAdapter
    private lateinit var libraryBinding: FragmentLibraryBinding
    private val libraryList = mutableListOf<LibraryEntity>()
    private val libraryViewModel by sharedViewModel<LibraryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_library, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        libraryBinding = FragmentLibraryBinding.bind(view)
        initRecyclerView()
        setupObservers()

        libraryBinding.add.setOnClickListener {
            StartLibraryFragment().show(parentFragmentManager, "hola")
        }
    }

    private fun initRecyclerView(){

        libraryBinding.rv.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        libraryAdapter = LibraryAdapter(libraryList)
        libraryBinding.rv.adapter = libraryAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupObservers(){
        libraryViewModel.insertLibrary("HOLA4", "Libreria 2")
        libraryViewModel.updateLibrariesLiveData()
        libraryViewModel.librariesLiveData.observe(viewLifecycleOwner){
            libraryAdapter.librariesList = it
            libraryAdapter.notifyDataSetChanged()
        }
    }
}