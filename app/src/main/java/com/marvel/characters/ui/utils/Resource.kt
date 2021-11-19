package com.marvel.characters.ui.utils

sealed class Resource <T>(
    val data: T? = null,
    val errorCode: Int? = null,
    val errorMessage: String? = null,
    val correlationId: String? = null,
    val tryAgainOn: Boolean? = null
) {
    class Success<T>(data: T? = null) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class GenericDataError<T>(errorCode: Int? = null, errorMessage: String? = null) :
        Resource<T>(null, errorCode, errorMessage)

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Loading<T> -> "Loading"
            is GenericDataError -> "Error[exception=$errorCode]"
        }
    }
}
