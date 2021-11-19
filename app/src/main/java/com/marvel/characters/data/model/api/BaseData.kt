package com.marvel.characters.data.model.api

class BaseData<T>(
    val offset: Int?,
    val limit: Int?,
    val total: Int?,
    val count: Int?,
    val results: List<T>?
)