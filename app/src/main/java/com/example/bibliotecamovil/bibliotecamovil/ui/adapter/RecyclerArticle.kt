package com.example.bibliotecamovil.bibliotecamovil.ui.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bibliotecamovil.R
import com.example.bibliotecamovil.bibliotecamovil.domain.model.Article
import com.example.bibliotecamovil.bibliotecamovil.utils.loadArticle
import com.example.bibliotecamovil.bibliotecamovil.utils.toast
import com.example.bibliotecamovil.databinding.ItemCardArticleBinding
import java.lang.Exception

class ArticleAdapter(var articleList: MutableList<Article>, val context: Context) :
    RecyclerView.Adapter<ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val articleBinding =
            ItemCardArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(articleBinding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articleList[position]
        val url = bind(holder, article)

        try {
            holder.binding.buttonArt.setOnClickListener {
                val uri = Uri.parse(url)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                context.startActivity(intent)
            }
        } catch (e: Exception) {
            context.toast(R.string.errorOpenMeli)
        }
    }



    override fun getItemCount(): Int = articleList.size

}


class ArticleViewHolder(val binding: ItemCardArticleBinding) : RecyclerView.ViewHolder(binding.root)

private fun bind(
    holder: ArticleViewHolder,
    article: Article
): String {
    val imageArticle = holder.binding.imageArticle
    val url = article.urlArticulo
    val imageId = article.imagen_id
    holder.binding.price.text = article.precio.toString()
    holder.binding.tittleArticle.text = article.titulo
    imageArticle.loadArticle(imageId)
    return url
}