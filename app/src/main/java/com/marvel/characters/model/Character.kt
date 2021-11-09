package com.marvel.characters.model

import java.util.*

data class Character(
    val id : Int,
    val name: String,
    val description: String,
    val modified : Date,
    val resourceURI : String,
    val urls : List<MarvelUrl>,
    val thumbnail : Thumbnail,
    val comics: Comic,
    val stories: Story,
    val events: Event,
    val series: Serie
)