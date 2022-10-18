package com.studio.noodoeassignment.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studio.noodoeassignment.data.GeneralResponse
import com.studio.noodoeassignment.data.LogInRequest
import com.studio.noodoeassignment.retrrofit.LonInFetcher
import kotlinx.coroutines.launch

class LogInViewModel : ViewModel() {

    private var logInResult = MutableLiveData<GeneralResponse>()
    fun getLogInResult(): LiveData<GeneralResponse> {
        return logInResult
    }

    fun fnLogIn(logInRequest: LogInRequest) {
        viewModelScope.launch {
            Log.d("AlexTest", "logInRequest = ${logInRequest}")
            val coroutineApiFetcher = LonInFetcher()
            try {
                val response = coroutineApiFetcher.fnLogIn(logInRequest)
                if (response.isSuccessful) {
                    val body = response.body()
                    Log.d("AlexTest", "Coroutine succeed, body = ${body}")
                    logInResult.postValue(GeneralResponse(true, ""))
                } else {
                    logInResult.postValue(GeneralResponse(false, response.errorBody().toString()))
                    Log.d(
                        "AlexTest",
                        "API Error, ${response.errorBody().toString()}"
                    )
                }
            } catch (ex: Exception) {
                Log.d("AlexTest", "APi Exception, ${ex}")
                logInResult.postValue(GeneralResponse(false, ex.toString()))
            }
        }
    }
}