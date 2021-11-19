package com.marvel.characters.domain.mappers

import com.marvel.characters.data.model.api.ApiCharacter
import com.marvel.characters.data.model.CharacterDetail
import com.marvel.characters.ui.utils.Resource

class ResourceCharacterDetailMapperImpl (
    private val characterDetailMapper: CharacterDetailMapper
        ): ResourceCharacterDetailMapper {

    override fun map(input: Resource<ApiCharacter?>): Resource<CharacterDetail> {
        return when(input){
            is Resource.Success->{
                Resource.Success(input.data?.let { characterDetailMapper.map(it) })
            }
            else ->Resource.GenericDataError(input.errorCode, input.errorMessage)
        }
    }

}