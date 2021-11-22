package com.marvel.characters.domain.mappers

import com.marvel.characters.data.model.Character
import com.marvel.characters.data.model.api.ApiCharacter
import com.marvel.characters.domain.di.AppModule
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.get

class CharacterListMapperUnitTest :KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(AppModule.mappersModule)
    }

    private lateinit var characterListMapper: CharacterListMapper

    @Before
    fun setUp(){
        characterListMapper = get()
    }


    @Test
    fun mappingNullListApiCharacterToListCharacter() {
        val result = characterListMapper.map(null)
        Assert.assertEquals(listOf<Character>(),result)
    }

    @Test
    fun mappingNonNullListApiCharacterToListCharacter() {
        val apiCharacter = ApiCharacter(
            null, null, null,
            null, null, null,
            null, null, null,
            null, null
        )
        val character = Character(
            0 ,"","", ".",
            0,0, 0, 0
        )
        val listApiCharacter = listOf(apiCharacter)
        val listCharacter = listOf(character)
        val result =  characterListMapper.map(listApiCharacter)
        Assert.assertEquals(listCharacter, result)
    }

}