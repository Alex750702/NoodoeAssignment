package com.studio.noodoeassignment.parkinglot

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.studio.noodoeassignment.data.ParkInfo
import com.studio.noodoeassignment.data.ParkStatus
import com.studio.noodoeassignment.retrrofit.ParkingLotFetcher

class ParkingLotRepository(private val iFetchApi: IFetchAPI) {

    interface IFetchAPI {
        fun onUpdateParkInfo(dataSource: ArrayList<ParkInfo>)
        fun onUpdateParkStatus(dataSource: ArrayList<ParkStatus>)
        fun onError(message: String)
    }

    private var parkInfo: ArrayList<ParkInfo> = arrayListOf()
    private var parkStatus: ArrayList<ParkStatus> = arrayListOf()

    suspend fun getParkInfo() {
        val coroutineApiFetcher = ParkingLotFetcher()
        try {
            val response = coroutineApiFetcher.fnGetParkingLot()
            if (response.isSuccessful) {
                val body = response.body()!!
                val parkJsonArray = body.getAsJsonObject("data").getAsJsonArray("park")
                val gson = Gson()
                val type = object : TypeToken<ArrayList<ParkInfo>>() {}.type
                val parkingLotList = gson.fromJson<ArrayList<ParkInfo>>(parkJsonArray, type)

                for (temp in parkingLotList) {
                    parkInfo.add(temp)
                }

                iFetchApi.onUpdateParkInfo(parkInfo)
            } else {
                iFetchApi.onError("Error+ ${response.errorBody().toString()}")
            }
        } catch (ex: Exception) {
            iFetchApi.onError("Exception: ${ex}")
        }

    }

    suspend fun getParkStatus() {
        val coroutineApiFetcher = ParkingLotFetcher()
        try {
            val response = coroutineApiFetcher.fnGetParkingLotStatus()
            if (response.isSuccessful) {
                val body = response.body()!!
                val statusJsonArray = body.getAsJsonObject("data").getAsJsonArray("park")
                val gson = Gson()
                val type = object : TypeToken<ArrayList<ParkStatus>>() {}.type
                val status = gson.fromJson<ArrayList<ParkStatus>>(statusJsonArray, type)
                for (temp in status) {
                    parkStatus.add(temp)
                }
                iFetchApi.onUpdateParkStatus(parkStatus)
            } else {
                iFetchApi.onError("Error+ ${response.errorBody().toString()}")
            }
        } catch (ex: Exception) {
            iFetchApi.onError("Exception: ${ex}")
        }

    }

}
