package com.example.bibliotecamovil.bibliotecamovil.domain.model

import com.google.gson.annotations.SerializedName

class Article(val id : String, val title : String, val price : Double, val condition : String, val available_quantity : Int,
              val thumbnail : String, val category_id : String, val currency_id : String, val shipping : Shipping,
val site_id : String)

class Shipping(val free_shipping : Boolean, val mode : String, val tags : ArrayList<String>, val logistic_type : String, val store_pick_up : Boolean)
