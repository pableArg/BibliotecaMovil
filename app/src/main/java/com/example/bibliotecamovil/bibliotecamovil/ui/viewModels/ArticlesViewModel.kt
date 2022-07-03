package com.example.bibliotecamovil.bibliotecamovil.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bibliotecamovil.bibliotecamovil.data.BookRepository
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retofit.Book
import com.example.bibliotecamovil.bibliotecamovil.domain.model.Article
import kotlinx.coroutines.launch

class ArticlesViewModel(private val bookRepository: BookRepository) : ViewModel() {

    private val articles = MutableLiveData<MutableList<Article>>()
private val errorMessage = MutableLiveData<String>()


    fun showArticles() : MutableLiveData<MutableList<Article>>{
        return this.articles
    }

    fun insertArticles(articleName : String){
        viewModelScope.launch {
            try {
                val response = bookRepository.searchArticleByName(articleName)
                if (response.isSuccessful && response.body() != null) {
                    articles.value = response.body()!!.results
                } else {
                    errorMessage.value = response.errorBody().toString()
                }

            }catch (e : Exception){
                errorMessage.value = e.message
            }
        }
    }

}