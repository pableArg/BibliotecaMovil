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
import com.example.bibliotecamovil.bibliotecamovil.ui.adapter.LibraryAdapter
import com.example.bibliotecamovil.bibliotecamovil.ui.fragments.*
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.*
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
        single { LibraryFavDatabase.getDatabase(get()).libraryDao() }
        single { BookRepository(get(), get(), get(),get(), get()) }
        single { SearchFragment() }
        single { LibraryAdapter(get())}
        single { FavouriteFragment() }
        single { BestSellerAPIClient() }
        single { BookAPIClient() }
        single { ArticleAPIClient() }
        single { DetailFragment() }
        single { LibraryFragment()}
        single{ArticlesFragment()}
        single { MainActivity() }


        //VIEWS MODELS
        viewModel { FavViewModel(get()) }
        viewModel { SearchViewModel(get()) }
        viewModel { DetailViewModel() }
        viewModel{ ArticlesViewModel(get())}
        viewModel { LibraryViewModel(get())}
    }
}



