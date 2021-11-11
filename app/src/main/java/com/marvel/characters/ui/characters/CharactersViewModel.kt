package com.marvel.characters.ui.characters

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.marvel.characters.R
import com.marvel.characters.repository.CharacterRepository
import com.marvel.characters.repository.CharacterRepositoryInterface
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CharactersViewModel constructor(
    //private val context: Context,
    private val characterRepository: CharacterRepositoryInterface
) : ViewModel(), KoinComponent {

    //val characterRepository: CharacterRepositoryInterface? = null
    private val _characters = MutableLiveData<JsonObject?>()
    val characters: LiveData<JsonObject?> = _characters

    fun getCharacters() {
        /*viewModelScope.launch {
            characterRepository?.getCharacters(
                context.getString(R.string.public_marvel_api_key),
                context.getString(R.string.private_marvel_api_key)
            )?.collect {
                _characters.value = it
            }
        }*/
    }
}