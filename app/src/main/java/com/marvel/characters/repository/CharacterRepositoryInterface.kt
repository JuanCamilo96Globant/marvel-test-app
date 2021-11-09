package com.marvel.characters.repository

import com.marvel.characters.model.BaseData
import com.marvel.characters.model.BaseResponse
import com.marvel.characters.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepositoryInterface {

    //suspend fun getCharacters() : Flow<BaseResponse<BaseData<List<Character>>>>

    //suspend fun getCharacterDetails(id: String) : Flow<BaseResponse<BaseData<List<Character>>>>
}