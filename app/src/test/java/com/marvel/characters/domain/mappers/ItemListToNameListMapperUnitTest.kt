package com.marvel.characters.domain.mappers

import com.marvel.characters.data.model.api.GenericItem
import com.marvel.characters.domain.di.AppModule
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.get

class ItemListToNameListMapperUnitTest : KoinTest{

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(AppModule.mappersModule)
    }

    private lateinit var itemListToNameListMapper: ItemListToNameListMapper

    @Before
    fun setUp(){
        itemListToNameListMapper = get()
    }


    @Test
    fun mappingNullListItemToName() {
        val result = itemListToNameListMapper.map(null)
        Assert.assertEquals(listOf<String>(),result)
    }

    @Test
    fun mappingNonNullListItemToName() {
        val item = GenericItem("","Marvel Universe",null)
        val list = listOf(item)
        val result =  itemListToNameListMapper.map(list)
        Assert.assertEquals(listOf("Marvel Universe"), result)
    }
}