package com.example.bibliotecamovil.bibliotecamovil.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bibliotecamovil.R
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.ArticleByName
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.Book
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.BookByAPI
import com.example.bibliotecamovil.bibliotecamovil.domain.model.ArticleResponse
import com.example.bibliotecamovil.bibliotecamovil.domain.model.BookResponse
import com.example.bibliotecamovil.bibliotecamovil.ui.adapter.BookAdapter
import com.example.bibliotecamovil.databinding.FragmentRecyclerCardsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*binding = FragmentRecyclerCardsBinding.inflate(LayoutInflater.from(this))
        initRecyclerView()
        setContentView(binding.root)

        binding.sv.setOnQueryTextListener(this)*/
    }

}

