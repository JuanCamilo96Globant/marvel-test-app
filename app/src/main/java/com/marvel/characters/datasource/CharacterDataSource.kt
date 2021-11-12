package com.marvel.characters.datasource

import com.google.gson.JsonObject
import com.marvel.characters.model.BaseData
import com.marvel.characters.model.BaseResponse
import com.marvel.characters.model.Character
import com.marvel.characters.service.ApiServiceGenerator

class CharacterDataSource
constructor(
    private val apiServiceGenerator: ApiServiceGenerator
): CharacterDataSourceInterface{

    private val recipesService = apiServiceGenerator.createService(ApiService::class.java)

    override suspend fun getCharacters(
        timeStand: String,
        apiKey: String,
        hash: String
    ): BaseResponse<BaseData<Character>>? {
        return apiServiceGenerator.processCall {
            recipesService.getCharacters(
                timeStand,
                apiKey,
                hash
            )
        }
    }

    override suspend fun getCharacterDetails(
        id: String,
        timeStand: String,
        apiKey: String,
        hash: String
    ): BaseResponse<BaseData<Character>>? {
        return apiServiceGenerator.processCall {
            recipesService.getCharacterDetails(
                id,
                timeStand,
                apiKey,
                hash
            )
        }
    }
}