package com.marvel.characters.datasource

import com.google.gson.JsonObject
import com.marvel.characters.model.BaseData
import com.marvel.characters.model.BaseResponse
import com.marvel.characters.model.Character
import retrofit2.http.Query

interface CharacterDataSourceInterface {

    suspend fun getCharacters(
        timeStand: String,
        apiKey: String,
        hash: String
    ): BaseResponse<BaseData<Character>>?

    suspend fun getCharacterDetails(
        id: String,
        timeStand: String,
        apiKey: String,
        hash: String
    ): BaseResponse<BaseData<Character>>?
}