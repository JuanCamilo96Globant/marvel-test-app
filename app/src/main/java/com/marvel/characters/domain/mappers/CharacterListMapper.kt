package com.marvel.characters.domain.mappers

import com.marvel.characters.data.model.api.ApiCharacter
import com.marvel.characters.data.model.Character

interface CharacterListMapper : GenericMapper<List<ApiCharacter>?,List<Character>>