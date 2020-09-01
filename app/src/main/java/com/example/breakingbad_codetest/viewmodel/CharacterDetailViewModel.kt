package com.example.breakingbad_codetest.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.breakingbad_codetest.database.DatabaseCharacter

class CharacterDetailViewModel: ViewModel(){

    // The internal MutableLiveData for the selected character
    private val _selectedProperty = MutableLiveData<DatabaseCharacter>()

    // The external LiveData for the SelectedCharacter
    val selectedProperty: LiveData<DatabaseCharacter>
        get() = _selectedProperty


    fun setProperty(characterProperty: DatabaseCharacter){
        _selectedProperty.value = characterProperty
    }

}
