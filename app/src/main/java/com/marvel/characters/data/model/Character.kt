package com.marvel.characters.data.model

data class Character(
    val id : Long,
    val name: String,
    val description: String,
    val photo : String,
    val comics: Int,
    val stories: Int,
    val events: Int,
    val series: Int
)
