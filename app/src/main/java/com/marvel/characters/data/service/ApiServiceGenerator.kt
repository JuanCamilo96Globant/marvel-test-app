package com.marvel.characters.data.service

import com.google.gson.Gson
import com.marvel.characters.data.model.api.ApiResponse
import com.marvel.characters.data.model.api.GenericErrorApiResponse
import com.marvel.characters.data.model.api.SuccessApiResponse
import com.marvel.characters.ui.utils.*
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiServiceGenerator constructor(
    private val baseUrl: String
){

    private lateinit var retrofit: Retrofit
    private val headerMap = mutableMapOf<String, String>()

    private var headerInterceptor = Interceptor { chain ->
        val original = chain.request()
        headerMap[CONTENT_TYPE] = CONTENT_TYPE_VALUE

        val headers = getHeaders().addAll(original.headers()).build()

        val request = original.newBuilder()
            .headers(headers)
            .method(original.method(), original.body())
            .build()

        chain.proceed(request)
    }

    init {
        setupRetrofit()
    }

    private fun setupRetrofit() {
        val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        okHttpBuilder.addInterceptor(headerInterceptor)
        okHttpBuilder.connectTimeout(TIME_OUT_CONNECT.toLong(), TimeUnit.SECONDS)
        okHttpBuilder.readTimeout(TIME_OUT_READ.toLong(), TimeUnit.SECONDS)
        val client = okHttpBuilder.build()
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl).client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getHeaders(): Headers.Builder {
        val header: Headers.Builder = Headers.Builder()
        headerMap.forEach { (key, value) ->
            header.add(key, value)
        }
        return header
    }

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }

    fun withHeader(key: String, value: String) {
        headerMap[key] = value
        setupRetrofit()
    }

    suspend fun <Data> processCallWithError(responseCall: suspend () -> Response<Data>): ApiResponse<Data?> {

        val gson = Gson()
        var response: Response<Data>? = null
        return try {
            response = responseCall.invoke()
            if (response.isSuccessful) {
                SuccessApiResponse(response.body())
            } else {
                return gson.fromJson(response.errorBody()?.string() ?: "", GenericErrorApiResponse::class.java)
                    ?: GenericErrorApiResponse(-2, "")
            }
        } catch (ex: Exception) {
            GenericErrorApiResponse(-2, ex.message)
        }
    }

}