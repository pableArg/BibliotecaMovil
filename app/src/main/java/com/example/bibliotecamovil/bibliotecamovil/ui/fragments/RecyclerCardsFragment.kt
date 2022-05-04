package com.example.bibliotecamovil.bibliotecamovil.ui.fragments

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bibliotecamovil.R
import com.example.bibliotecamovil.bibliotecamovil.ui.adapter.LibraryAdapter
import com.example.bibliotecamovil.databinding.FragmentRecyclerCardsBinding


class RecyclerCardsFragment : Fragment() {

    private lateinit var binding: FragmentRecyclerCardsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = setContentView(
            this.context as Activity,
            R.layout.fragment_recycler_cards

        )

        val rv = binding.rv
        val adapter = LibraryAdapter(list)

        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(activity)

        val sv = binding.sv

        sv.queryHint = "Buscar libro"



        return inflater.inflate(R.layout.fragment_recycler_cards, container, false)
    }


    companion object {

        val list = arrayOf(
            "Libro1", "Libro2", "Libro3", "Libro4",
            "Libro5", "Libro6", "Libro7", "Libro8", "Libro9",
            "Libro10", "Libro11", "Libro12"

        )
    }
}