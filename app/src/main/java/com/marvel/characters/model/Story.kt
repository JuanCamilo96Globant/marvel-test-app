package com.marvel.characters.model

data class Story(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<GenericItem>
)
