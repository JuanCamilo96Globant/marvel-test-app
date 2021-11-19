package com.marvel.characters.data.datasource

import com.marvel.characters.data.model.api.ApiCharacter
import com.marvel.characters.ui.utils.Resource

interface CharacterDataSource {

    suspend fun getCharacters(): Resource<List<ApiCharacter>?>

    suspend fun getCharacterDetails(
        id: String
    ): Resource<ApiCharacter?>
}