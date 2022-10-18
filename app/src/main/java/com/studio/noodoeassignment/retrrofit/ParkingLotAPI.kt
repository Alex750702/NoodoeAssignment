package com.studio.noodoeassignment.retrrofit

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET

interface ParkingLotAPI {

    @GET("blobtcmsv/TCMSV_alldesc.json")
    suspend fun getParkInfo(): Response<JsonObject>

    @GET("blobtcmsv/TCMSV_allavailable.json")
    suspend fun getParkStatus(): Response<JsonObject>
}