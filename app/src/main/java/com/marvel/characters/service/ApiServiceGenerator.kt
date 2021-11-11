package com.marvel.characters.service

import com.google.gson.JsonObject
import com.marvel.characters.utils.contentType
import com.marvel.characters.utils.contentTypeValue
import com.marvel.characters.utils.network.NetworkConnectivityUtilsInterface
import com.marvel.characters.utils.timeoutConnect
import com.marvel.characters.utils.timeoutRead
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiServiceGenerator constructor(
    private val baseUrl: String,
    private val networkConnectivityUtils: NetworkConnectivityUtilsInterface
){

    private lateinit var retrofit: Retrofit
    private val headerMap = mutableMapOf<String, String>()

    private var headerInterceptor = Interceptor { chain ->
        val original = chain.request()
        headerMap[contentType] = contentTypeValue

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
        okHttpBuilder.connectTimeout(timeoutConnect.toLong(), TimeUnit.SECONDS)
        okHttpBuilder.readTimeout(timeoutRead.toLong(), TimeUnit.SECONDS)
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

    suspend fun processCall(responseCall : suspend () -> Response<JsonObject?>):JsonObject?{
        return responseCall.invoke().body()
    }

    /*suspend fun <Data> processCallWithError(responseCall: suspend () -> Response<Data>): BaseResponse<Data?> {
        if (!networkConnectivityUtils.isConnected()) {
            return GenericErrorApiResponse(
                500,
                this.networkConnectivity.getNetworkInfo().toString()
            )
        }
        val jsonAdapter: JsonAdapter<ErrorApiResponse> =
            getMoshi().adapter(ErrorApiResponse::class.java)
        var response: Response<Data>? = null
        return try {
            response = responseCall.invoke()
            if (response.isSuccessful) {
                SuccessApiResponse(response.body())
            } else {
                return jsonAdapter.fromJson(response.errorBody()?.string() ?: "")
                    ?: GenericErrorApiResponse(-2, "")
            }
        } catch (npe: NullPointerException) {
            return jsonAdapter.fromJson(response?.errorBody()?.string() ?: "")
                ?: GenericErrorApiResponse(-2, npe.message)
        } catch (ex: Exception) {
            GenericErrorApiResponse(-2, ex.message)
        }
    }*/

}