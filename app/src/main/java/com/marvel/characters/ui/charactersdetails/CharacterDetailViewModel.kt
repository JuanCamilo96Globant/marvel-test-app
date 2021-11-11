package com.marvel.characters.ui.charactersdetails

import androidx.lifecycle.ViewModel
import com.marvel.characters.repository.CharacterRepositoryInterface
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CharacterDetailViewModel constructor(
    private val characterRepository: CharacterRepositoryInterface
) : ViewModel(), KoinComponent {

}