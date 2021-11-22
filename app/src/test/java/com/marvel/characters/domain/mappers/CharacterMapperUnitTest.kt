package com.marvel.characters.domain.mappers

import com.marvel.characters.data.model.Character
import com.marvel.characters.data.model.CharacterDetail
import com.marvel.characters.data.model.api.ApiCharacter
import com.marvel.characters.domain.di.AppModule
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.get

class CharacterMapperUnitTest : KoinTest{

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(AppModule.mappersModule)
    }

    private lateinit var characterMapper: CharacterMapper

    @Before
    fun setUp(){
        characterMapper = get()
    }

    @Test
    fun mappingNonNullApiCharacterToCharacter() {
        val apiCharacter = ApiCharacter(
            null, null, null,
            null, null, null,
            null, null, null,
            null, null
        )
        val expected = Character(
            0 ,"","", ".",
            0,0, 0, 0
        )
        val result =  characterMapper.map(apiCharacter)
        Assert.assertEquals(expected,result)
    }

}