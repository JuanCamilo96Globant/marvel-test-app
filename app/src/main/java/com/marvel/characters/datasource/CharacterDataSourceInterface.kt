package com.marvel.characters.datasource

import com.google.gson.JsonObject

interface CharacterDataSourceInterface {

    suspend fun getCharacters(
        publicApiKey: String,
        privateApiKey: String
    ): JsonObject?

    suspend fun getCharacterDetails(
        id: String,
        publicApiKey: String,
        privateApiKey: String
    ): JsonObject?
}