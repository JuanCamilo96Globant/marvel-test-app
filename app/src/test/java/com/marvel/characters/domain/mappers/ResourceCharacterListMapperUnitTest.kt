package com.marvel.characters.domain.mappers

import com.marvel.characters.data.model.api.ApiCharacter
import com.marvel.characters.domain.di.AppModule
import com.marvel.characters.ui.utils.Resource
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.get

class ResourceCharacterListMapperUnitTest : KoinTest{

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(AppModule.mappersModule)
    }

    private lateinit var resourceCharacterListMapper: ResourceCharacterListMapper

    @Before
    fun setUp(){
        resourceCharacterListMapper = get()
    }

    @Test
    fun mappingSuccessResourceNullListApiCharacterToSuccessResourceListCharacter() {
        val result = resourceCharacterListMapper.map(Resource.Success(null))
        Assert.assertNotNull(result.data)
    }

    @Test
    fun mappingSuccessResourceNonNullListApiCharacterToSuccessResourceListCharacter() {
        val apiCharacter = ApiCharacter(
            null, null, null,
            null, null, null,
            null, null, null,
            null, null
        )
        val result = resourceCharacterListMapper.map(Resource.Success(listOf(apiCharacter)))
        Assert.assertNotNull(result)
    }

    @Test
    fun mappingErrorResourceListApiCharacterToErrorResourceListCharacter() {
        val result = resourceCharacterListMapper.map(
            Resource.GenericDataError(
            0,
            "")
        )
        Assert.assertNotNull(result)
    }
}