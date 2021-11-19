package com.marvel.characters.data.datasource

import com.marvel.characters.data.model.api.BaseData
import com.marvel.characters.data.model.api.BaseResponse
import com.marvel.characters.data.model.api.ApiCharacter
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("characters")
    suspend fun getCharacters(
        @Query("ts") timeStand: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String
    ): Response<BaseResponse<BaseData<ApiCharacter>>>

    @GET("characters/{characterId}")
    suspend fun getCharacterDetails(
        @Path("characterId") id: String,
        @Query("ts") timeStand: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String
    ): Response<BaseResponse<BaseData<ApiCharacter>>>

}