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

class LibraryViewModel(private val bookRepository: BookRepository) : ViewModel() {
    val librariesLiveData = MutableLiveData<MutableList<LibraryEntity>>()
    private val errorMessage = MutableLiveData<String>()


    fun updateLibrariesLiveData(){
        viewModelScope.launch {
            try{
                val libraryList = bookRepository.getAllLibraries()
                if(libraryList.isEmpty()){
                    throw ( Exception("Ha ocurrido un error"))
                }
                else{
                    librariesLiveData.value = libraryList
                }
            } catch (e : Exception){
                Firebase.crashlytics.recordException(e)
                errorMessage.value = e.message
            }
        }
    }

    fun insertLibrary(idLibrary : String, name : String){
        CoroutineScope(Dispatchers.IO).launch {
            bookRepository.insertLibraryInDatabase(idLibrary, name)
        }
    }
}