package com.example.bibliotecamovil.bibliotecamovil.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.graphics.vector.addPathNodes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bibliotecamovil.R
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retofit.ArticleAPIClient
import com.example.bibliotecamovil.bibliotecamovil.domain.model.Article
import com.example.bibliotecamovil.bibliotecamovil.ui.activities.MainActivity
import com.example.bibliotecamovil.bibliotecamovil.ui.adapter.ArticleAdapter
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.ArticlesViewModel
import com.example.bibliotecamovil.databinding.FragmentArticleBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ArticlesFragment : Fragment(){

    private lateinit var articleBinding : FragmentArticleBinding
    private lateinit var articleAdapter : ArticleAdapter
    private var articleList = mutableListOf<Article>()
    private val articleModel by sharedViewModel<ArticlesViewModel>()
    private val args by navArgs<ArticlesFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        articleBinding = FragmentArticleBinding.bind(view)
        initRecyclerView()
        articleModel.insertArticles(args.nameArticle)
        setupObservers()
        (activity as MainActivity).supportActionBar?.title = "Mercado Libre"
    }

    private fun initRecyclerView(){
        articleBinding.rvArt.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        articleAdapter = ArticleAdapter(articleList, this.requireContext())
        articleBinding.rvArt.adapter = articleAdapter
    }

    @SuppressLint("NotifyDataSetCHanged")
    private fun setupObservers(){
        articleModel.showArticles()
            .observe(viewLifecycleOwner){
            articleAdapter.articleList = it
                articleAdapter.notifyDataSetChanged()
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        articleAdapter.articleList.clear()
        articleAdapter.notifyDataSetChanged()

    }
}