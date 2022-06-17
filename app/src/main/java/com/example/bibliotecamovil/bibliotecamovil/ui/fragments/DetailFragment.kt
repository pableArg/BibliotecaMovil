package com.example.bibliotecamovil.bibliotecamovil.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.bibliotecamovil.R
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.DetailViewModel
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.SearchViewModel
import com.example.bibliotecamovil.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso


class DetailFragment (): Fragment() {
    private lateinit var detailBinding : FragmentDetailBinding
    private val detailModel : DetailViewModel by activityViewModels() {DetailViewModel.Factory()}
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
      //   detailBinding.imageBook =
             val imagenDetail = detailModel.bookDetail.value?.libroInfo?.imagenes?.imagen
        detailBinding.tittleInfo.text = detailModel.bookDetail.value?.libroInfo?.titulo ?: ""
         /*Picasso.get()
            .load("https://books.google.com/books/content?id=$imagenDetail&printsec=frontcover&img=1&zoom=1&source=gbs_api")
            .placeholder(R.drawable.notfound)
            .into(detailBinding.imageBook)*/
        // SETEO EL LOGO DE FAVOTIOS Y EL LISTENER
        // CHEQUEO CON val esFav: Boolean = checkFavourite.intoFavs(detailModel.bookDetail.value?.idBook)
        // inserto/elimino con checkFavourite.addOrDeleteNewMovieFav(detailModel.bookDetail.value?.idBook)
    }


}