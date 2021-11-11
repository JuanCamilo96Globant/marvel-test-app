package com.marvel.characters.datasource

import com.google.gson.JsonObject
import retrofit2.http.Query

interface CharacterDataSourceInterface {

    suspend fun getCharacters(
        timeStand: String,
        apiKey: String,
        hash: String
    ): JsonObject?

    suspend fun getCharacterDetails(
        id: String,
        timeStand: String,
        apiKey: String,
        hash: String
    ): JsonObject?
}