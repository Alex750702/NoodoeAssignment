package com.studio.noodoeassignment.retrrofit

import com.studio.noodoeassignment.data.LogInRequest
import com.studio.noodoeassignment.data.LogInResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LogInAPI {

    @Headers("X-Parse-Application-Id:vqYuKPOkLQLYHhk4QTGsGKFwATT4mBIGREI2m8eD")
    @POST("api/login")
    suspend fun login(@Body request: LogInRequest): Response<LogInResponse>
}