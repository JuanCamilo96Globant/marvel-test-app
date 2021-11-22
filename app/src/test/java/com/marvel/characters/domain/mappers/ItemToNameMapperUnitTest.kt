package com.marvel.characters.domain.mappers

import com.marvel.characters.data.model.api.GenericItem
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ItemToNameMapperUnitTest {

    private lateinit var itemToNameMapper: ItemToNameMapper

    @Before
    fun setUp(){
        itemToNameMapper = ItemToNameMapperImpl()
    }

    @Test
    fun mappingNullItemToName() {
        val result = itemToNameMapper.map(null)
        Assert.assertEquals("", result)
    }

    @Test
    fun mappingNonNullItemToName() {
        val item = GenericItem("","Marvel Universe",null)
        val result =  itemToNameMapper.map(item)
        Assert.assertEquals("Marvel Universe", result)
    }
}