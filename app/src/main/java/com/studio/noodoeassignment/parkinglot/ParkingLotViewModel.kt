package com.studio.noodoeassignment.parkinglot

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.studio.noodoeassignment.data.ParkInfo
import com.studio.noodoeassignment.data.ParkInfoUI
import com.studio.noodoeassignment.data.ParkStatus
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ParkingLotViewModel : ViewModel() {

    enum class StatusStatus(val desc: String) {
        CHARGING("充電中"),
        IDLE("待機中")
    }

    private var parkInfoUIList = MutableLiveData<HashMap<String, ParkInfoUI>>()
    fun getParkInfoUIList(): LiveData<HashMap<String, ParkInfoUI>> {
        return parkInfoUIList
    }

    private var parkingLotRepository =
        ParkingLotRepository(object : ParkingLotRepository.IFetchAPI {
            override fun onUpdateParkInfo(dataSource: ArrayList<ParkInfo>) {

                val map = HashMap<String, ParkInfoUI>()
                for (temp in dataSource) {
                    map[temp.id!!] = ParkInfoUI(
                        "id : ${temp.id!!}",
                        "name : ${temp.name!!}",
                        "address : ${temp.address!!}",
                        "totalcar : ${temp.totalcar!!}"
                    )
                }
                parkInfoUIList.postValue(map)
            }

            override fun onUpdateParkStatus(dataSource: ArrayList<ParkStatus>) {

                val map = parkInfoUIList.value!!
                for (temp in dataSource) {
                    temp.availablecar.let {
                        if (map[temp.id] != null) {
                            map[temp.id!!]!!.availableCar = "availableCar : ${temp.availablecar}"
                        }
                    }
                    var charging = 0
                    var idle = 0
                    temp.chargeStation?.let { it ->
                        for (socket in it.scoketStatusList!!) {
                            when (socket.spot_status) {
                                StatusStatus.CHARGING.desc -> {
                                    charging++
                                }
                                StatusStatus.IDLE.desc -> {
                                    idle++
                                }
                            }
                        }
                        map.let {
                            it[temp.id!!]!!.chargeStationCharging = "充電中總數 : ${charging}"
                            it[temp.id!!]!!.chargeStationIdle = "待機中總數: ${idle}"
                        }

                    }
                }
                parkInfoUIList.postValue(map)
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
