package com.marvel.characters.ui.charactersdetails

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marvel.characters.data.model.CharacterDetail
import com.marvel.characters.domain.usecases.GetCharacterDetailsUseCase
import com.marvel.characters.ui.utils.Resource
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class CharacterDetailViewModel constructor(
    private val context: Context,
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase
) : ViewModel(), KoinComponent {

    private val _characterDetail = MutableLiveData<Resource<CharacterDetail>>()
    val characterDetail: LiveData<Resource<CharacterDetail>> = _characterDetail

    fun getCharacter(itemId: String) {
        viewModelScope.launch {
            _characterDetail.value = Resource.Loading()
            _characterDetail.value = getCharacterDetailsUseCase(itemId)!!
        }
    }
}