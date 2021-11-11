package com.marvel.characters.repository

import com.google.gson.JsonObject
import com.marvel.characters.model.BaseData
import com.marvel.characters.model.BaseResponse
import com.marvel.characters.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepositoryInterface {

    suspend fun getCharacters(
        timeStand: String,
        apiKey: String,
        hash: String
    ) : Flow<JsonObject?>

    suspend fun getCharacterDetails(
        id: String,
        timeStand: String,
        apiKey: String,
        hash: String
    ) : Flow<JsonObject?>

}