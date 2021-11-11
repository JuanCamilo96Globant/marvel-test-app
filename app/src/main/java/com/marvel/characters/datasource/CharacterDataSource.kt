package com.marvel.characters.datasource

import com.google.gson.JsonObject
import com.marvel.characters.service.ApiServiceGenerator

class CharacterDataSource
constructor(
    private val apiServiceGenerator: ApiServiceGenerator
): CharacterDataSourceInterface{

    private val recipesService = apiServiceGenerator.createService(ApiService::class.java)

    override suspend fun getCharacters(publicApiKey: String, privateApiKey: String): JsonObject? {
        return apiServiceGenerator.processCall {
            recipesService.getCharacters(
                publicApiKey,
                privateApiKey
            )
        }
    }

    override suspend fun getCharacterDetails(
        id: String,
        publicApiKey: String,
        privateApiKey: String
    ): JsonObject? {
        return apiServiceGenerator.processCall {
            recipesService.getCharacterDetails(
                id,
                publicApiKey,
                privateApiKey
            )
        }
    }
}