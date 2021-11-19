package com.marvel.characters.domain.mappers

import com.marvel.characters.ui.utils.Resource

interface GenericMapper<I,O> {

    fun map(input: I):O

}