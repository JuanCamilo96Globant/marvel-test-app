package com.marvel.characters.domain.mappers

import com.marvel.characters.data.model.api.ApiCharacter
import com.marvel.characters.data.model.CharacterDetail

interface CharacterDetailMapper: GenericMapper<ApiCharacter?, CharacterDetail>