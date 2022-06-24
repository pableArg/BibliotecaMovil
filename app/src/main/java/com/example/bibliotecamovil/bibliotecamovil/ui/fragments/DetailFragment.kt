package com.example.bibliotecamovil.bibliotecamovil.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import com.example.bibliotecamovil.R
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.DetailViewModel
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.FavViewModel
import com.example.bibliotecamovil.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class DetailFragment : Fragment() {
    private lateinit var detailBinding: FragmentDetailBinding
    private val detailModel by sharedViewModel<DetailViewModel>()
    private val favModel by sharedViewModel<FavViewModel>()

    //private val checkFavourite: CheckFavorite
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
        detailBinding = FragmentDetailBinding.bind(view)
        setupDetail()
        setIcon(detailModel.bookDetail.value!!.id)
    }


    private fun setupDetail() {
        val book = detailModel.bookDetail.value
        detailBinding.txtTitleDetail.text = book!!.libroInfo.titulo
        Picasso.get()
            .load("https://books.google.com/books/content?id=${book.id}&printsec=frontcover&img=1&zoom=1&source=gbs_api")
            .placeholder(R.drawable.notfound)
            .into(detailBinding.imageView2)

        detailBinding.btnSelectFavourite.setOnClickListener {

            if(favModel.deleteOrInsert(book)) {
                setIconTrue(detailModel.bookDetail.value!!.id)
            }
            else {
                setIconFalse(detailModel.bookDetail.value!!.id)

            }
        }
    }

    private fun setIconTrue(idBook: String) {
        detailBinding.btnSelectFavourite.background =
            getDrawable(requireActivity(), R.drawable.ic_fav_pressed)

    }
    private fun setIconFalse(idBook: String) {
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


    /*private fun setIcon(idBook: String) {
        if (favModel.idFavoritos.contains(idBook)) {
            detailBinding.btnSelectFavourite.background =
                getDrawable(requireActivity(), R.drawable.ic_fav_pressed)
        } else {
            detailBinding.btnSelectFavourite.background =
                getDrawable(requireActivity(), R.drawable.ic_fav_default)
        }
    }*/
}
