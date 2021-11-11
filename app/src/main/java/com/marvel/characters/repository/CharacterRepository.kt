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
        timeStand: String,
        apiKey: String,
        hash: String
    ): Flow<JsonObject?> {
        return flow {
            val response = characterDataSource.getCharacters(
                timeStand,
                apiKey,
                hash
            )
            emit(response)
        }.flowOn(ioDispatcher)
    }

    override suspend fun getCharacterDetails(
        id: String,
        timeStand: String,
        apiKey: String,
        hash: String
    ): Flow<JsonObject?> {
        return flow {
            val response = characterDataSource.getCharacterDetails(
                id,
                timeStand,
                apiKey,
                hash
            )
            emit(response)
        }.flowOn(ioDispatcher)
    }
}