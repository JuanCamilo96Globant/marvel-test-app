package com.marvel.characters.data.model.api

data class Event(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<GenericItem>
)