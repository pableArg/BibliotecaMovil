package com.example.bibliotecamovil.bibliotecamovil.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bibliotecamovil.R
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retofit.Book
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.DetailViewModel
import com.example.bibliotecamovil.bibliotecamovil.utils.loadBooks
import com.example.bibliotecamovil.bibliotecamovil.utils.toast
import com.example.bibliotecamovil.databinding.ItemCardBinding
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase


class BookAdapter(
    var bookList: MutableList<Book>,
    private val context: Context,
    private val detailModel: DetailViewModel,
    private val onClickListener: View.OnClickListener,
) :

    RecyclerView.Adapter<BookViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val bookBinding =
            ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return BookViewHolder(bookBinding)

    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = bookList[position]
        bind(holder, book)
        try {
            holder.binding.cardView.setOnClickListener { view ->
                detailModel.bookDetail.value = book
                onClickListener.onClick(view);
            }
        } catch (e: Exception) {
            context.toast(R.string.snackError)
            Firebase.crashlytics.recordException(e)
        }
    }


    override fun getItemCount(): Int = bookList.size

}

class BookViewHolder(val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root)

private fun bind(
    holder: BookViewHolder,
    book: Book
) {
    holder.binding.tittleBook.text = book.libroInfo.titulo
    val idLibro = book.id
    val image = holder.binding.imageBook
    image.loadBooks(idLibro)
}


