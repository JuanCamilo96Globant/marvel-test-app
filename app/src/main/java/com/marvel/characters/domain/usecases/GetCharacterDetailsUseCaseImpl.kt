package com.marvel.characters.domain.usecases

import com.marvel.characters.data.model.Character
import com.marvel.characters.data.model.CharacterDetail
import com.marvel.characters.domain.repository.CharacterRepository
import com.marvel.characters.ui.utils.Resource
import kotlinx.coroutines.flow.collect

class GetCharacterDetailsUseCaseImpl(
    private val characterRepository: CharacterRepository
) : GetCharacterDetailsUseCase {

    lateinit var characterDetails : Resource<CharacterDetail>

    override suspend fun invoke(id: String): Resource<CharacterDetail> {
        characterRepository.getCharacterDetails(id).collect {
            characterDetails = it
        }
        return characterDetails
    }

}