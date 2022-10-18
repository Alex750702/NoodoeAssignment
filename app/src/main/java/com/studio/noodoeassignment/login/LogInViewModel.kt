package com.studio.noodoeassignment.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studio.noodoeassignment.data.LogInRequest
import com.studio.noodoeassignment.data.LogInResponse
import com.studio.noodoeassignment.retrrofit.LonInFetcher
import kotlinx.coroutines.launch

class LogInViewModel : ViewModel() {

    private var logInResult = MutableLiveData<LogInResponse>()
    fun getLogInResult(): LiveData<LogInResponse> {
        return logInResult
    }

    private var errorMessage = MutableLiveData<String>()
    fun getErrorMessage(): LiveData<String> {
        return errorMessage
    }

    fun fnLogIn(logInRequest: LogInRequest) {
        viewModelScope.launch {
            val apiFetcher = LonInFetcher()
            try {
                val response = apiFetcher.fnLogIn(logInRequest)
                if (response.isSuccessful) {
                    val body = response.body()
                    logInResult.postValue(body!!)
                } else {
                    errorMessage.postValue(response.errorBody().toString())
                }
            } catch (ex: Exception) {
                errorMessage.postValue(ex.toString())
            }
        }
    }
}