package com.marvel.characters.domain.mappers

import com.marvel.characters.data.model.CharacterDetail
import com.marvel.characters.data.model.api.*
import com.marvel.characters.domain.di.AppModule
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.get

class CharacterDetailMapperUnitTest :KoinTest{

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(AppModule.mappersModule)
    }

    private lateinit var characterDetailMapper: CharacterDetailMapper

    @Before
    fun setUp(){
        characterDetailMapper = get()
    }


    @Test
    fun mappingNullApiCharacterToDetailCharacter() {
        val expected = CharacterDetail(0,"","", ".",
            listOf(), listOf(), listOf(), listOf())
        val result = characterDetailMapper.map(null)
        Assert.assertEquals(expected,result)
    }

    @Test
    fun mappingNonNullApiCharacterToDetailCharacter() {
        val apiCharacter = ApiCharacter(
            null, null, null,
            null, null, null,
            null, null, null,
            null, null
        )
        val result =  characterDetailMapper.map(apiCharacter)
        Assert.assertNotNull(result)
    }
}