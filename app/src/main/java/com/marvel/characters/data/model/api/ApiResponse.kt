package com.marvel.characters.data.model.api

sealed class ApiResponse<out Data>

data class SuccessApiResponse<out Data>(val body: Data) : ApiResponse<Data>()

data class GenericErrorApiResponse(val statusCode: Int? = -1, val message: String? = "") :
    ApiResponse<Nothing>()