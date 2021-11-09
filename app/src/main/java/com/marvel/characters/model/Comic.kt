package com.marvel.characters.model

data class Comic(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<GenericItem>
)