package com.marvel.characters.domain.mappers

import com.marvel.characters.data.model.api.ApiCharacter
import com.marvel.characters.data.model.CharacterDetail

class CharacterDetailMapperImpl (
    private val itemListToNameListMapper: ItemListToNameListMapper
        ): CharacterDetailMapper {

    override fun map(input: ApiCharacter?): CharacterDetail {
        return CharacterDetail(
            input?.id ?: 0,
            input?.name ?: "",
            input?.description ?: "",
            "${input?.thumbnail?.path ?: ""}.${input?.thumbnail?.extension ?: ""}",
            itemListToNameListMapper.map(input?.comics?.items),
            itemListToNameListMapper.map(input?.stories?.items),
            itemListToNameListMapper.map(input?.events?.items),
            itemListToNameListMapper.map(input?.series?.items)
        )
    }

}