package com.marvel.characters.domain.mappers

import com.marvel.characters.data.model.api.GenericItem

interface ItemListToNameListMapper :GenericMapper<List<GenericItem>?,List<String>>