package com.example.bibliotecamovil.bibliotecamovil.injectDependencies

import android.app.Application
import com.example.bibliotecamovil.bibliotecamovil.data.database.BookFavDAO
import com.example.bibliotecamovil.bibliotecamovil.data.database.LibraryFavDatabase
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retofit.BestSellerAPIClient
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retofit.BookAPIClient
import com.example.bibliotecamovil.bibliotecamovil.data.BookRepository
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retofit.ArticleAPIClient
import com.example.bibliotecamovil.bibliotecamovil.ui.activities.MainActivity
import com.example.bibliotecamovil.bibliotecamovil.ui.adapter.ArticleAdapter
import com.example.bibliotecamovil.bibliotecamovil.ui.adapter.BookAdapter
import com.example.bibliotecamovil.bibliotecamovil.ui.fragments.ArticlesFragment
import com.example.bibliotecamovil.bibliotecamovil.ui.fragments.DetailFragment
import com.example.bibliotecamovil.bibliotecamovil.ui.fragments.FavouriteFragment
import com.example.bibliotecamovil.bibliotecamovil.ui.fragments.SearchFragment
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.ArticlesViewModel
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.DetailViewModel
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.FavViewModel
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.SearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class BookApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BookApp)
            fragmentFactory()
            modules(appModule)
        }
    }

    val appModule = module {
        factory { BookAdapter(get(), get(), get(), get()) }
        factory {  ArticleAdapter(get(), get()) }
        single { LibraryFavDatabase.getDatabase(get()).bookFavDao() }
        single { BookRepository(get(), get(), get(),get()) }
        single { SearchFragment() }
        single { FavouriteFragment() }
        single { BestSellerAPIClient() }
        single { BookAPIClient() }
        single { ArticleAPIClient() }
        single { DetailFragment() }
        single{ArticlesFragment()}
        single { MainActivity() }


        //VIEWS MODELS
        viewModel { FavViewModel(get()) }
        viewModel { SearchViewModel(get()) }
        viewModel { DetailViewModel() }
        viewModel{ ArticlesViewModel(get())}
    }
}



