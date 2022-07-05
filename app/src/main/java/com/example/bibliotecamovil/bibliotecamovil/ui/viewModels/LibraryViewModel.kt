package com.example.bibliotecamovil.bibliotecamovil.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bibliotecamovil.bibliotecamovil.data.BookRepository
import com.example.bibliotecamovil.bibliotecamovil.data.database.LibraryEntity
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LibraryViewModel(val bookRepository: BookRepository) : ViewModel() {
    val librariesLiveData = MutableLiveData<MutableList<String>>()
    private val errorMessage = MutableLiveData<String>()
    private var idLibraries = mutableListOf<String>()

    fun updateLibrariesLiveData(){
        CoroutineScope(Dispatchers.IO).launch {
            idLibraries = bookRepository.getAllLibrariesFromDatabase()
        }
        librariesLiveData.value = idLibraries
        }


    fun insertLibrary(idLibrary : String){
        CoroutineScope(Dispatchers.IO).launch {
            bookRepository.insertLibraryInDatabase(idLibrary)
        }
    }
}