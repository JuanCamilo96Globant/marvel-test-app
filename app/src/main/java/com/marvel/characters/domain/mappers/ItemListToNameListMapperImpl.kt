package com.marvel.characters.domain.mappers

import com.marvel.characters.data.model.api.GenericItem

class ItemListToNameListMapperImpl(
    private val itemToNameMapper: ItemToNameMapper
):ItemListToNameListMapper {
    override fun map(input: List<GenericItem>?): List<String> {
        return input?.map { itemToNameMapper.map(it) }.orEmpty()
    }
}