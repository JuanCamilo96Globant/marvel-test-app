package com.marvel.characters.ui.charactersdetails

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
import com.marvel.characters.repository.CharacterRepositoryInterface
import com.marvel.characters.utils.Utils
import com.marvel.characters.utils.timeStand
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class CharacterDetailViewModel constructor(
    private val context: Context,
    private val characterRepository: CharacterRepositoryInterface
) : ViewModel(), KoinComponent {

    private val _character = MutableLiveData<Character?>()
    val character: LiveData<Character?> = _character

    fun getCharacter() {
        viewModelScope.launch {
            characterRepository?.getCharacterDetails(
                "1011334",
                timeStand.toString(),
                context.getString(R.string.public_marvel_api_key),
                Utils.buildHashMd5(
                    timeStand.toString()
                            +context.getString(R.string.private_marvel_api_key)
                            +context.getString(R.string.public_marvel_api_key)
                )
            )?.collect {
                if (it != null) {
                    _character.value = it.data.results?.get(0)
                }
            }
        }
    }

}