package com.marvel.characters.domain.repository

import com.marvel.characters.data.model.*
import com.marvel.characters.ui.utils.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface CharacterRepository {

    suspend fun getCharacters(
    ) : Flow<Resource<List<Character>>>

    suspend fun getCharacterDetails(
        id: String
    ) : Flow<Resource<CharacterDetail>>

}