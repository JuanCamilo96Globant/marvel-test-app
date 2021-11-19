package com.marvel.characters.domain.mappers

import com.marvel.characters.data.model.api.ApiCharacter
import com.marvel.characters.data.model.Character
import com.marvel.characters.ui.utils.Resource

interface ResourceCharacterListMapper
    : GenericMapper<Resource<List<ApiCharacter>?>, Resource<List<Character>>>