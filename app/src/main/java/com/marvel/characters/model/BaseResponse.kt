package com.marvel.characters.model

open class BaseResponse<T>(
    code: Int?,
    status: String?,
    copyright: String?,
    attributionText: String?,
    attributionHTML: String?,
    etag: String?,
    data: T
){
    val code = code
    val status = status
    val copyright = copyright
    val attributionText = attributionText
    val attributionHTML = attributionHTML
    val etag = etag
    val data =data
}