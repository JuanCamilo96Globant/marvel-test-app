package com.marvel.characters.datasource

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("characters")
    suspend fun getCharacters(
        @Query("ts") publicApiKey: String,
        @Query("hash") privateApiKey: String
    ): Response<JsonObject?>

    @GET("characters/{characterId}")
    suspend fun getCharacterDetails(
        @Path("characterId") id: String,
        @Query("ts") publicApiKey: String,
        @Query("hash") privateApiKey: String
    ): Response<JsonObject?>

}