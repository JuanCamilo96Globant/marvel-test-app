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

class ResourceCharacterDetailMapperUnitTest : KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(AppModule.mappersModule)
    }

    private lateinit var resourceCharacterDetailMapper: ResourceCharacterDetailMapper

    @Before
    fun setUp(){
        resourceCharacterDetailMapper = get()
    }


    @Test
    fun mappingSuccessResourceNullApiCharacterToSuccessResourceCharacter() {
        val result = resourceCharacterDetailMapper.map(Resource.Success(null))
        Assert.assertNotNull(result.data)
    }

    @Test
    fun mappingSuccessResourceNonNullApiCharacterToSuccessResourceCharacter() {
        val apiCharacter = ApiCharacter(
            null, null, null,
            null, null, null,
            null, null, null,
            null, null
        )
        val result = resourceCharacterDetailMapper.map(Resource.Success(apiCharacter))
        Assert.assertNotNull(result)
    }

    @Test
    fun mappingErrorResourceApiCharacterToErrorResourceCharacter() {
        val result = resourceCharacterDetailMapper.map(Resource.GenericDataError(
            0,
            "")
        )
        Assert.assertNotNull(result)
    }

}