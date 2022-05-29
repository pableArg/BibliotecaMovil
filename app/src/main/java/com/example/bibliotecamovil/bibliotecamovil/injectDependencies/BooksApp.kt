package com.example.bibliotecamovil.bibliotecamovil.injectDependencies

import android.app.Application

class BooksApp : Application() {
    //FALTAN LAS DEPENDENCIAS DE KOIN PARA IMPLEMENTAR
    /*
    val appModule = module {
        //OTRAS CLASES INYECTADAS
        single{ MoviesAdapter }
        single<BookFavDAO>{ LibraryFavDatabase.getInstance(get()).userDAO()}
        single{ BookRepository() }

        //VIEWS MODELS
        viewModel { FavViewModel(get(),get()) }
        viewModel { SearchViewModel(get()) }
        viewModel { HomeViewModel(get()) }
        viewModel { SignUpViewModel(get()) }
        viewModel { LogInViewModel(get()) }
        viewModel { UserViewModel(get()) }
        viewModel { MovieDetailsViewModel(get(),get()) }
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