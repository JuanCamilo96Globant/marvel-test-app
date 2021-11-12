package com.marvel.characters.ui.characters

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.marvel.characters.R
import com.marvel.characters.model.BaseData
import com.marvel.characters.model.BaseResponse
import com.marvel.characters.model.Character
import com.marvel.characters.repository.CharacterRepository
import com.marvel.characters.repository.CharacterRepositoryInterface
import com.marvel.characters.utils.Utils
import com.marvel.characters.utils.timeStand
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CharactersViewModel constructor(
    private val context: Context,
    private val characterRepository: CharacterRepositoryInterface
) : ViewModel(), KoinComponent {

    private val _characters = MutableLiveData<BaseResponse<BaseData<Character>>?>()
    val characters: LiveData<BaseResponse<BaseData<Character>>?> = _characters

    fun getCharacters() {
        viewModelScope.launch {
            characterRepository?.getCharacters(
                timeStand.toString(),
                context.getString(R.string.public_marvel_api_key),
                Utils.buildHashMd5(
                    timeStand.toString()
                            +context.getString(R.string.private_marvel_api_key)
                            +context.getString(R.string.public_marvel_api_key)
                )
            )?.collect {
                _characters.value = it
            }
        }
    }
}