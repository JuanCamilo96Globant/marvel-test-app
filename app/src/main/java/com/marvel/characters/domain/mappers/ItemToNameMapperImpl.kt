package com.marvel.characters.domain.mappers

import com.marvel.characters.data.model.api.GenericItem

class ItemToNameMapperImpl: ItemToNameMapper{

    override fun map(input: GenericItem?): String {
        return input?.name ?: ""
    }

}