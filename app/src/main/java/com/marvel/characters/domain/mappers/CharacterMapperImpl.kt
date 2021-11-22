package com.marvel.characters.domain.mappers

import com.marvel.characters.data.model.api.ApiCharacter
import com.marvel.characters.data.model.Character

class CharacterMapperImpl : CharacterMapper {
    override fun map(input: ApiCharacter): Character {
        return Character(
            input.id ?: 0 ,
            input.name ?: "",
            input.description ?: "",
            "${input.thumbnail?.path?: ""}.${input.thumbnail?.extension?: ""}",
            input.comics?.items?.size ?: 0,
            input.stories?.items?.size ?: 0,
            input.events?.items?.size ?: 0,
            input.series?.items?.size ?: 0
        )
    }
}