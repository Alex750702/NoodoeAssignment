package com.studio.noodoeassignment.parkinglot

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.studio.noodoeassignment.data.ParkInfo
import com.studio.noodoeassignment.data.ParkStatus
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ParkingLotViewModel : ViewModel() {

    private var parkInfoList = MutableLiveData<ArrayList<ParkInfo>>()
    fun getParkInfo(): LiveData<ArrayList<ParkInfo>> {
        return parkInfoList
    }

    private var parkStatus = MutableLiveData<ArrayList<ParkStatus>>()
    fun getParkStatus(): LiveData<ArrayList<ParkStatus>> {
        return parkStatus
    }

    private var parkingLotRepository =
        ParkingLotRepository(object : ParkingLotRepository.IFetchAPI {
            override fun onUpdatePark(dataSource: ArrayList<ParkInfo>) {
                parkInfoList.postValue(dataSource)
            }

            override fun onUpdateStatus(dataSource: ArrayList<ParkStatus>) {
                parkStatus.postValue(dataSource)
            }

            override fun onError(message: String) {
                Log.d("AlexTest", "error = $message")
            }
        })

    init {

        GlobalScope.launch {
            val taskGetPark = async {
                parkingLotRepository.getParkInfo()
            }
            val taskGetParkInfo = async {
                taskGetPark.await()
                parkingLotRepository.getParkStatus()
            }
        }
    }

}
