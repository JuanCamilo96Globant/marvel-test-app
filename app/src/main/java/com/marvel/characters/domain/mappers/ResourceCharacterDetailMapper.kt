package com.marvel.characters.domain.mappers

import com.marvel.characters.data.model.api.ApiCharacter
import com.marvel.characters.data.model.CharacterDetail
import com.marvel.characters.ui.utils.Resource

interface ResourceCharacterDetailMapper
    : GenericMapper<Resource<ApiCharacter?>, Resource<CharacterDetail>>