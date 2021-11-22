package com.marvel.characters.domain.mappers

interface GenericMapper<I,O> {

    fun map(input: I):O

}