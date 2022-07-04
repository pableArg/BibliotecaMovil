package com.example.bibliotecamovil.bibliotecamovil.data.repositories.retofit

import com.example.bibliotecamovil.bibliotecamovil.domain.model.BestSellerResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BestSellerAPIClient {

    private val serviceGetBestSellerByAPI : BookAPI = Retrofit.Builder()
        .baseUrl("https://api.nytimes.com/svc/books/v3/lists/current/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(BookAPI::class.java)


    suspend fun getBestSeller(nameList: String) : Response<BestSellerResponse> {
        return serviceGetBestSellerByAPI.getBestSellers(nameList)
    }
}