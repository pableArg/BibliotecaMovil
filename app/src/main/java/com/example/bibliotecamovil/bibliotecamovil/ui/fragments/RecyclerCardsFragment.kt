package com.example.bibliotecamovil.bibliotecamovil.ui.fragments

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bibliotecamovil.R
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.Book
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.BookByAPI
import com.example.bibliotecamovil.bibliotecamovil.domain.model.BookResponse
import com.example.bibliotecamovil.bibliotecamovil.ui.adapter.LibraryAdapter
import com.example.bibliotecamovil.databinding.FragmentRecyclerCardsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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
        val adapter =  LibraryAdapter()
        val rv = binding.rv
        val sv = binding.sv

        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(activity)



            BookByAPI().getLibros("Rayuela", object: Callback<BookResponse> {

            override fun onFailure(call: Call<BookResponse>, t: Throwable) {
            }
            override fun onResponse(call: Call<BookResponse>, response: Response<BookResponse>) {
                if (response.isSuccessful) {

                    val received = response.body()
                    if (received != null) {
                        adapter.list = received
                        adapter.notifyDataSetChanged()

                    }

                }
            }})

        return inflater.inflate(R.layout.fragment_recycler_cards, container, false)
    }

}