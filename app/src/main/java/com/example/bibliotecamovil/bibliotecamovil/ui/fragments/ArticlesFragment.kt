package com.example.bibliotecamovil.bibliotecamovil.ui.fragments

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bibliotecamovil.R
import com.example.bibliotecamovil.bibliotecamovil.domain.model.Article
import com.example.bibliotecamovil.bibliotecamovil.ui.activities.MainActivity
import com.example.bibliotecamovil.bibliotecamovil.ui.adapter.ArticleAdapter
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.ArticlesViewModel
import com.example.bibliotecamovil.databinding.FragmentArticleBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ArticlesFragment : Fragment() {

    private lateinit var articleBinding: FragmentArticleBinding
    private lateinit var articleAdapter: ArticleAdapter
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
        articleModel.insertArticles(args.nameArticle)
        setupObservers()
        customOrientation()

    }


    @SuppressLint("NotifyDataSetCHanged")
    private fun setupObservers() {
        articleModel.showArticles()
            .observe(viewLifecycleOwner) {
                articleAdapter.articleList = it
                articleAdapter.notifyDataSetChanged()
            }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onDestroyView() {
        super.onDestroyView()
        articleAdapter.articleList.clear()
        articleAdapter.notifyDataSetChanged()

    }

    private fun customOrientation() {
        when (resources.configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                (activity as MainActivity).supportActionBar?.hide()
                articleBinding.textArt.visibility = View.INVISIBLE
                setGridLayout()
            }
            Configuration.ORIENTATION_PORTRAIT -> {
                (activity as MainActivity).supportActionBar?.title = args.nameArticle
                setLinearLayout()
            }

        }

    }

    private fun setLinearLayout() {
        articleBinding.rvArt.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        articleAdapter = ArticleAdapter(articleList, this.requireContext())
        articleBinding.rvArt.adapter = articleAdapter

    }

    private fun setGridLayout() {
        articleBinding.rvArt.layoutManager = GridLayoutManager(this.context, 2)
        LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        articleAdapter = ArticleAdapter(articleList, this.requireContext())
        articleBinding.rvArt.adapter = articleAdapter

    }
}