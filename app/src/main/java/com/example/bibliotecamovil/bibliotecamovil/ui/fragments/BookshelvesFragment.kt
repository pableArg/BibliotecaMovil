package com.example.bibliotecamovil.bibliotecamovil.ui.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bibliotecamovil.R
import com.example.bibliotecamovil.databinding.FragmentBookshelfBinding
import com.example.bibliotecamovil.databinding.FragmentBookshelvesBinding

class BookshelvesFragment : Fragment(){

    private lateinit var bookshelvesFragment : FragmentBookshelvesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookshelves, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bookshelvesFragment = FragmentBookshelvesBinding.bind(view)

    }


}