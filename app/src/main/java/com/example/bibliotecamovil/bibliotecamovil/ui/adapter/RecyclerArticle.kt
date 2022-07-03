package com.example.bibliotecamovil.bibliotecamovil.ui.adapter

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.bibliotecamovil.R
import com.example.bibliotecamovil.bibliotecamovil.domain.model.Article
import com.example.bibliotecamovil.databinding.ItemCardArticleBinding
import com.squareup.picasso.Picasso
import java.lang.Exception
import kotlin.coroutines.coroutineContext

class ArticleAdapter(var articleList : MutableList<Article>, val context : Context)
    : RecyclerView.Adapter<ArticleViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val articleBinding =
            ItemCardArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return ArticleViewHolder(articleBinding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articleList[position]
        val url = article.urlArticulo

        holder.binding.price.text = article.precio.toString()

        try{
            Picasso.get()
                .load("https://http2.mlstatic.com/D_896200-MLA31453103659_072019-I.jpg")
                .placeholder(R.drawable.notfound)
                .into(holder.binding.imageArticle)

            holder.binding.tittleArticle.text = article.titulo

            holder.binding.buttonArt.setOnClickListener {
                val uri = Uri.parse(url)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                context.startActivity(intent)
            }

        } catch(e : Exception){
            Toast.makeText(context,"No es posible abrir el articulo", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = articleList.size

}


class ArticleViewHolder(val binding : ItemCardArticleBinding) : RecyclerView.ViewHolder(binding.root)