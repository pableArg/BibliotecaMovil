package com.example.bibliotecamovil.bibliotecamovil.injectDependencies

import android.app.Application
import com.example.bibliotecamovil.bibliotecamovil.data.BookRepository
import com.example.bibliotecamovil.bibliotecamovil.data.database.BookFavDAO
import com.example.bibliotecamovil.bibliotecamovil.data.database.LibraryFavDatabase
import com.example.bibliotecamovil.bibliotecamovil.ui.adapter.BookAdapter
import com.example.bibliotecamovil.bibliotecamovil.ui.adapter.BookFavAdapter
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.FavViewModel

class BooksApp : Application() {
    //FALTAN LAS DEPENDENCIAS DE KOIN PARA IMPLEMENTAR

    /*val appModule = module {
        //OTRAS CLASES INYECTADAS
        single{ BookAdapter }
        single{ BookFavAdapter }
        single<BookFavDAO>{ LibraryFavDatabase.getInstance(get()).userDAO()}
        single{ BookRepository() }

        //VIEWS MODELS
        viewModel { FavViewModel(get(),get()) }

    }
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BooksApp)
            modules(appModule)
        }
    }*/
}