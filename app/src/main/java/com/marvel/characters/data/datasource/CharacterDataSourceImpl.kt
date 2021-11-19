package com.marvel.characters.data.datasource

import android.content.Context
import com.marvel.characters.R
import com.marvel.characters.data.model.api.GenericErrorApiResponse
import com.marvel.characters.data.model.api.SuccessApiResponse
import com.marvel.characters.data.model.api.ApiCharacter
import com.marvel.characters.data.model.api.BaseData
import com.marvel.characters.data.model.api.BaseResponse
import com.marvel.characters.data.service.ApiServiceGenerator
import com.marvel.characters.ui.utils.Resource
import com.marvel.characters.ui.utils.TIME_STAND
import com.marvel.characters.ui.utils.Utils

class CharacterDataSourceImpl
constructor(
    private val apiServiceGenerator: ApiServiceGenerator,
    private val context: Context,
) : CharacterDataSource {

    private val recipesService = apiServiceGenerator.createService(ApiService::class.java)

    override suspend fun getCharacters(): Resource<List<ApiCharacter>?> {
        return when (val response =
            apiServiceGenerator.processCallWithError {
                recipesService.getCharacters(
                    TIME_STAND.toString(),
                    context.getString(R.string.public_marvel_api_key),
                    Utils.buildHashMd5(
                        TIME_STAND.toString()
                                + context.getString(R.string.private_marvel_api_key)
                                + context.getString(R.string.public_marvel_api_key)

                    )
                )
            }) {
            is SuccessApiResponse<*> -> {
                val body = response.body as BaseResponse<BaseData<ApiCharacter>>
                Resource.Success(body.data?.results)
            }
            else -> {
                Resource.GenericDataError(
                    (response as GenericErrorApiResponse).statusCode,
                    response.message
                )
            }
        }
    }

    override suspend fun getCharacterDetails(
        id: String
    ): Resource<ApiCharacter?> {
        return when (val response =
            apiServiceGenerator.processCallWithError {
                recipesService.getCharacterDetails(
                    id,
                    TIME_STAND.toString(),
                    context.getString(R.string.public_marvel_api_key),
                    Utils.buildHashMd5(
                        TIME_STAND.toString()
                                + context.getString(R.string.private_marvel_api_key)
                                + context.getString(R.string.public_marvel_api_key)
                    )
                )
            }) {
            is SuccessApiResponse<*> -> {
                val body = response.body as BaseResponse<BaseData<ApiCharacter>>
                Resource.Success(body.data?.results?.get(0))
            }
            else -> {
                Resource.GenericDataError(
                    (response as GenericErrorApiResponse).statusCode,
                    response.message
                )
            }
        }
    }
}