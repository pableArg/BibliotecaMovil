package com.example.bibliotecamovil.bibliotecamovil.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.bibliotecamovil.R
import com.example.bibliotecamovil.bibliotecamovil.domain.model.CheckFavorite
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.DetailViewModel
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.SearchViewModel
import com.example.bibliotecamovil.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class DetailFragment (private val checkFavourite: CheckFavorite): Fragment() {
    private lateinit var detailBinding : FragmentDetailBinding
    private val detailModel : DetailViewModel by sharedViewModel<DetailViewModel>()
    /*activityViewModels() {DetailViewModel.Factory()}*/
    //INYECCIÓN CheckFavourite
    //private val checkFavourite: CheckFavourite by injected()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDetail()
    }

    private fun setupDetail (){
        val book = detailModel.bookDetail.value
        detailBinding.tittleInfo.text= book?.libroInfo?.titulo
        Picasso.get()
            .load("https://books.google.com/books/content?id=${book?.id}&printsec=frontcover&img=1&zoom=1&source=gbs_api")
            .placeholder(R.drawable.notfound)
            .into(detailBinding.imagenInfo)
        // SETEO EL LOGO DE FAVOTIOS Y EL LISTENER
        if(book != null && checkFavourite.intoFavs(book.id)){
            //detailBinding./* imageFav*/ = setImageEstaEnFav
        }else{
            //detailBinding./* imageFav*/ = setImageEstaEnFav
        }
        /*detailBinding. /* imageFav */.setOnClickListener{*/ checkFavourite.addOrDeleteNewMovieFav(book!!) /*}*/
    }


}