package com.marvel.characters.domain.usecases

import com.marvel.characters.data.model.Character
import com.marvel.characters.domain.repository.CharacterRepository
import com.marvel.characters.ui.utils.Resource
import kotlinx.coroutines.flow.collect

class GetCharactersUseCaseImpl(
    private val characterRepository: CharacterRepository
) : GetCharactersUseCase {

    lateinit var charactersResource : Resource<List<Character>>

    override suspend fun invoke(): Resource<List<Character>> {
        characterRepository.getCharacters().collect {
            charactersResource = it
        }
        return charactersResource
    }

}