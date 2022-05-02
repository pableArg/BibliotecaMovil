package com.example.bibliotecamovil.bibliotecamovil.domain.sharedPreferences

import android.content.Context
import android.content.SharedPreferences

class Preferences (private val context: Context){
    private val sharedPrefs: SharedPreferences = context.getSharedPreferences("my_settings", Context.MODE_PRIVATE)

    fun saveName(name: String){
        sharedPrefs.edit().putString("name",name).apply()
    }

    fun getName(): String{
        return sharedPrefs.getString("name","")!!
    }

    fun wipe(){
        sharedPrefs.edit().clear().apply()
    }
}