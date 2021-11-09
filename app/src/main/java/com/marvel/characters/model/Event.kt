package com.marvel.characters.model

data class Event(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<GenericItem>
)