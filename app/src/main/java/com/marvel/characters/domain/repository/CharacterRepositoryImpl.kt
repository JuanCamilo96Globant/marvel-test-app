package com.marvel.characters.domain.repository

import com.marvel.characters.data.datasource.CharacterDataSource
import com.marvel.characters.data.model.*
import com.marvel.characters.domain.mappers.ResourceCharacterDetailMapper
import com.marvel.characters.domain.mappers.ResourceCharacterListMapper
import com.marvel.characters.ui.utils.Resource
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CharacterRepositoryImpl
constructor(
    private val characterDataSource: CharacterDataSource,
    private val resourceCharacterListMapper: ResourceCharacterListMapper,
    private val resourceCharacterDetailMapper: ResourceCharacterDetailMapper,
    private val ioDispatcher: CoroutineContext
) : CharacterRepository {

    override suspend fun getCharacters(

    ): Flow<Resource<List<Character>>> {
        return flow {
            val response = characterDataSource.getCharacters()
            emit(resourceCharacterListMapper.map(response))
        }.flowOn(ioDispatcher)
    }

    override suspend fun getCharacterDetails(
        id: String
    ): Flow<Resource<CharacterDetail>> {
        return flow {
            val response = characterDataSource.getCharacterDetails(id)
            emit(resourceCharacterDetailMapper.map(response))
        }.flowOn(ioDispatcher)
    }
}