package com.marvel.characters.repository

import android.content.Context
import com.google.gson.JsonObject
import com.marvel.characters.R
import com.marvel.characters.datasource.CharacterDataSource
import com.marvel.characters.datasource.CharacterDataSourceInterface
import com.marvel.characters.model.BaseData
import com.marvel.characters.model.BaseResponse
import com.marvel.characters.model.Character
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.koin.java.KoinJavaComponent.inject

class CharacterRepository
constructor(
    private val characterDataSource: CharacterDataSourceInterface,
    private val ioDispatcher: CoroutineContext
) : CharacterRepositoryInterface {

    override suspend fun getCharacters(
        publicApiKey: String,
        privateApiKey: String
    ): Flow<JsonObject?> {
        return flow {
            val response = characterDataSource.getCharacters(
                publicApiKey,
                privateApiKey
            )
            emit(response)
        }.flowOn(ioDispatcher)
    }

    override suspend fun getCharacterDetails(
        id: String,
        publicApiKey: String,
        privateApiKey: String
    ): Flow<JsonObject?> {
        return flow {
            val response = characterDataSource.getCharacterDetails(
                id,
                publicApiKey,
                privateApiKey
            )
            emit(response)
        }.flowOn(ioDispatcher)
    }
}