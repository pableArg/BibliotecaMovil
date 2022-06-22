package com.example.bibliotecamovil.bibliotecamovil.injectDependencies

import android.app.Application
import androidx.room.RoomDatabase
import com.example.bibliotecamovil.bibliotecamovil.data.database.BookFavDAO
import com.example.bibliotecamovil.bibliotecamovil.data.database.LibraryFavDatabase
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.BookAPIClient
import com.example.bibliotecamovil.bibliotecamovil.data.repositories.retrofit.BookRepository
import com.example.bibliotecamovil.bibliotecamovil.domain.model.CheckFavorite
import com.example.bibliotecamovil.bibliotecamovil.ui.adapter.BookAdapter
import com.example.bibliotecamovil.bibliotecamovil.ui.adapter.BookFavAdapter
import com.example.bibliotecamovil.bibliotecamovil.ui.fragments.DetailFragment
import com.example.bibliotecamovil.bibliotecamovil.ui.fragments.FavouriteFragment
import com.example.bibliotecamovil.bibliotecamovil.ui.fragments.SearchFragment
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.DetailViewModel
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.FavViewModel
import com.example.bibliotecamovil.bibliotecamovil.ui.viewModels.SearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory

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
        factory { BookAdapter(get(),get(),get(),get()) }
        single { BookFavAdapter(get()) }
        //single{ LibraryFavDatabase.getDatabase(get()) }
        single<BookFavDAO>{ LibraryFavDatabase.getDatabase(get()).bookFavDao()}
        factory { BookRepository(get(), get()) }
        single { CheckFavorite(get()) }
        single { SearchFragment() }
        single { FavouriteFragment() }
        single { BookAPIClient() }
        single { DetailFragment() }

        //VIEWS MODELS
        viewModel { FavViewModel(get()) }
        viewModel { SearchViewModel(get()) }
        viewModel { DetailViewModel() }
    }
}



