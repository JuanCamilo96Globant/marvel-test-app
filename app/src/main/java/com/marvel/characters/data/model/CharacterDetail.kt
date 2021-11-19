package com.marvel.characters.data.model

data class CharacterDetail(
    val id : Long,
    val name: String,
    val description: String,
    val photo : String,
    val comics: List<String>,
    val stories: List<String>,
    val events: List<String>,
    val series: List<String>
)
