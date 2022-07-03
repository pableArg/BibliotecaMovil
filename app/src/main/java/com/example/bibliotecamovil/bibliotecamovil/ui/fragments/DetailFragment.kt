package com.example.bibliotecamovil.bibliotecamovil.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.navigation.fragment.findNavController
import com.example.bibliotecamovil.R
import com.example.bibliotecamovil.bibliotecamovil.ui.activities.MainActivity
import com.example.bibliotecamovil.bibliotecamovil.ui.adapter.ArticleViewHolder
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.ArticlesViewModel
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.DetailViewModel
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.FavViewModel
import com.example.bibliotecamovil.databinding.FragmentDetailBinding
import com.google.android.material.transition.MaterialContainerTransform
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class DetailFragment : Fragment() {
    private lateinit var detailBinding: FragmentDetailBinding
    private val detailModel by sharedViewModel<DetailViewModel>()
    private val favModel by sharedViewModel<FavViewModel>()
    private val articleModel by sharedViewModel<ArticlesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        detailBinding = FragmentDetailBinding.bind(view)
        setupDetail()
        setIcon(detailModel.bookDetail.value!!.id)
    }


    @SuppressLint("SetTextI18n")
    private fun setupDetail() {
        val book = detailModel.bookDetail.value
        detailBinding.txtTitleDetail.text = book!!.libroInfo.titulo
        Picasso.get()
            .load("https://books.google.com/books/content?id=${book.id}&printsec=frontcover&img=1&zoom=1&source=gbs_api")
            .placeholder(R.drawable.notfound)
            .into(detailBinding.imageView2)
        detailBinding.txtAuthorDetail.text="Autor: ${book.libroInfo.autores[0]}"
        detailBinding.txtEditDetail.text="Editorial: ${book.libroInfo.editorial}"
        detailBinding.txtKindDetail.text="Origen: ${book.libroVenta.pais}"
        detailBinding.txtSynopsisDetail.text =  book.libroInfo.descripcion
        detailBinding.txtFechaPublicacionDetail.text="Fecha de publicación: ${book.libroInfo.fechaPublicacion}"
        (activity as MainActivity).supportActionBar?.title = book.libroInfo.titulo

        detailBinding.btnSelectFavourite.setOnClickListener {

            if(favModel.deleteOrInsert(book)) {
                setIconTrue()
            }
            else {
                setIconFalse()
            }
        }
        detailBinding.buttonML.setOnClickListener {
            findNavController().navigate(DetailFragmentDirections.actionDetailFragment2ToArticlesFragment(book.libroInfo.titulo))
        }
    }

    private fun setIconTrue() {
        detailBinding.btnSelectFavourite.background =
            getDrawable(requireActivity(), R.drawable.ic_fav_pressed)

    }
    private fun setIconFalse() {
        detailBinding.btnSelectFavourite.background =
            getDrawable(requireActivity(), R.drawable.ic_fav_default)
    }

    private fun setIcon(idBook: String) {
        favModel.idFavoritosLiveData.observe(viewLifecycleOwner) {
            if (it.contains(idBook)) {
                detailBinding.btnSelectFavourite.background =
                    getDrawable(requireActivity(), R.drawable.ic_fav_pressed)
            }
            else{
                detailBinding.btnSelectFavourite.background =
                    getDrawable(requireActivity(), R.drawable.ic_fav_default)
            }
        }
    }

}
