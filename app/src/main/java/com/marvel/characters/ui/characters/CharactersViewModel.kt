package com.marvel.characters.ui.characters

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marvel.characters.data.model.Character
import com.marvel.characters.domain.usecases.GetCharactersUseCase
import com.marvel.characters.ui.utils.Resource
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class CharactersViewModel constructor(
    private val context: Context,
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel(), KoinComponent {

    private val _characters = MutableLiveData<Resource<List<Character>>>()
    val characters: LiveData<Resource<List<Character>>> = _characters

    fun getCharacters() {
        viewModelScope.launch {
            _characters.value = Resource.Loading()
            _characters.value = getCharactersUseCase()!!
        }
    }
}