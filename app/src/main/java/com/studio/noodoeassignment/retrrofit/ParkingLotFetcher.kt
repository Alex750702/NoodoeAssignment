package com.studio.noodoeassignment.retrrofit

import android.util.Log
import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ParkingLotFetcher {

    private var logging = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            Log.d("okhttp", "interceptor msg $message")
        }
    })

    private lateinit var retrofitApi: ParkingLotAPI
    private var okHttpClient: OkHttpClient
    private val baseUrl = "https://tcgbusfs.blob.core.windows.net/"

    init {
        logging.level = HttpLoggingInterceptor.Level.BODY
        okHttpClient = OkHttpClient()
            .newBuilder()
            .addInterceptor(logging)
            .build()

        fnSetRetrofit()
    }

    private fun fnSetRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofitApi = retrofit.create(ParkingLotAPI::class.java)
    }

    suspend fun fnGetParkingLot(): Response<JsonObject> {
        return retrofitApi.getParkInfo()
    }

    suspend fun fnGetParkingLotStatus(): Response<JsonObject> {
        return retrofitApi.getParkStatus()
    }
}