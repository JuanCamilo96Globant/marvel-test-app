package com.marvel.characters.data.model.api

import java.util.Date

data class ApiCharacter(
    val id : Long?,
    val name: String?,
    val description: String?,
    val modified : Date?,
    val resourceURI : String?,
    val urls : List<MarvelUrl>?,
    val thumbnail : Thumbnail?,
    val comics: Comic?,
    val stories: Story?,
    val events: Event?,
    val series: Serie?
)