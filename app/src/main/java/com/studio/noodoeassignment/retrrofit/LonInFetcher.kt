package com.studio.noodoeassignment.retrrofit

import android.util.Log
import com.studio.noodoeassignment.data.LogInRequest
import com.studio.noodoeassignment.data.LogInResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LonInFetcher {

    private var logging = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            Log.d("okhttp", "interceptor msg $message")
        }
    })

    private lateinit var retrofitApi: LogInAPI
    private var okHttpClient: OkHttpClient
    private val baseUrl = "https://noodoe-app-development.web.app/"

    init {
        logging.level = HttpLoggingInterceptor.Level.BODY
        okHttpClient = OkHttpClient()
            .newBuilder()
            .addInterceptor(logging)
            .build()

        setRetrofit()
    }

    private fun setRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofitApi = retrofit.create(LogInAPI::class.java)
    }

    suspend fun logIn(
        request: LogInRequest
    ): Response<LogInResponse> {
        return retrofitApi.login(request)
    }
}