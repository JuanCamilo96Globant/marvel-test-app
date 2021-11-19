package com.marvel.characters.domain.mappers

import com.marvel.characters.data.model.api.ApiCharacter
import com.marvel.characters.data.model.Character

class CharacterListMapperImpl (
    private val characterMapper: CharacterMapper
        ): CharacterListMapper {

    override fun map(input: List<ApiCharacter>?): List<Character> {
        return return input?.map { characterMapper.map(it) }.orEmpty()
    }

}