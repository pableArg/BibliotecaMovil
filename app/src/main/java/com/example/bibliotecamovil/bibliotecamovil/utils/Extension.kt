package com.example.bibliotecamovil.bibliotecamovil.utils

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.StringRes
import com.example.bibliotecamovil.R
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso


fun ImageView.load(idLibro: String) {
    Picasso.get()
        .load("https://books.google.com/books/content?id=$idLibro&printsec=frontcover&img=1&zoom=1&source=gbs_api")
        .placeholder(R.drawable.notfound)
        .into(this)
}

fun Context.tost(text: Int, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, length).show()
}


fun View.showSnackbar(
    @StringRes messageRes: Int,
    length: Int = Snackbar.LENGTH_LONG,
    f: Snackbar.() -> Unit
) {
    val snackBar = Snackbar.make(this, resources.getString(messageRes), length)
    snackBar.f()
    snackBar.show()
}

fun Snackbar.action(@StringRes actionRes: Int, color: Int? = null, listener: (View) -> Unit) {
    setAction(actionRes, listener)
    color?.let { setActionTextColor(color) }
}


