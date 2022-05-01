package com.example.bibliotecamovil.bibliotecamovil.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.bibliotecamovil.R
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.ArticleByName
import com.example.bibliotecamovil.bibliotecamovil.domain.model.ArticleResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*ArticleByName().getArticulos("", object: Callback<ArticleResponse> {
            override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                findViewById<TextView>(R.id.nombreLibro).setText(t.message)

            }
            override fun onResponse(call: Call<ArticleResponse>, response: Response<ArticleResponse>) {
                if (response.isSuccessful) {
                    val received = response.body()
                    if (received != null) {
                        findViewById<TextView>(R.id.nombreLibro).setText(received.results.get(1).price.toString())
                    }

                }
            }})*/
    }
}