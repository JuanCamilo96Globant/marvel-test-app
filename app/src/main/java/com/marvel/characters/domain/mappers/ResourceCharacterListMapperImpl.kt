package com.marvel.characters.domain.mappers

import com.marvel.characters.data.model.api.ApiCharacter
import com.marvel.characters.data.model.Character
import com.marvel.characters.ui.utils.Resource

class ResourceCharacterListMapperImpl (
    private val characterListMapper: CharacterListMapper
        ):ResourceCharacterListMapper {

    override fun map(input: Resource<List<ApiCharacter>?>): Resource<List<Character>> {
        return when(input){
            is Resource.Success->{
                Resource.Success(input.data?.let { characterListMapper.map(it) })
            }
            else ->Resource.GenericDataError(input.errorCode, input.errorMessage)
        }
    }
}