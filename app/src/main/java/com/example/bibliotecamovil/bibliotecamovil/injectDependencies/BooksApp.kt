package com.example.bibliotecamovil.bibliotecamovil.injectDependencies

import android.app.Application
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.BookRepository
import com.example.bibliotecamovil.bibliotecamovil.data.database.LibraryFavDatabase
import com.example.bibliotecamovil.bibliotecamovil.domain.model.VerificarEnFavoritos
import com.example.bibliotecamovil.bibliotecamovil.ui.adapter.BookAdapter
import com.example.bibliotecamovil.bibliotecamovil.ui.adapter.BookFavAdapter
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.DetailViewModel
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.FavViewModel
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.SearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class BooksApp : Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BooksApp)
            modules(appModule)
        }
    }
}

val appModule = module {
    single { BookAdapter(get()) }
    single { BookFavAdapter(get(), get()) }
    single { LibraryFavDatabase }
    single { BookRepository(get(), get()) }
    single { VerificarEnFavoritos(get()) }

    //VIEWS MODELS
    viewModel { FavViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { DetailViewModel() }

}