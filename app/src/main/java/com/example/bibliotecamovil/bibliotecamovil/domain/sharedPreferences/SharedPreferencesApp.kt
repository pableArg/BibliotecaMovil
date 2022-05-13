package com.example.bibliotecamovil.bibliotecamovil.domain.sharedPreferences

import android.app.Application

class SharedPreferencesApp: Application() {
    companion object {
        lateinit var preferences: Preferences
    }
    override fun onCreate() {
        super.onCreate()
        preferences = Preferences(applicationContext)
    }
}