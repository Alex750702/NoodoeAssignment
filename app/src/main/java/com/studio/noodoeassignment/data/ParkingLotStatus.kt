package com.studio.noodoeassignment.data

import com.google.gson.annotations.SerializedName

data class ParkingLotStatus(
    @SerializedName("UPDATETIME")
    var uPDATETIME: String? = null,

    @SerializedName("park")
    var parkStatus: ArrayList<ParkStatus>? = null
)

data class ChargeStation(
    var scoketStatusList: ArrayList<ScoketStatusList>? = null
)


data class ParkStatus(
    var id: String? = null,
    var availablecar: Int = 0,
    var availablemotor: Int = 0,
    var availablebus: Int = 0,

    @SerializedName("ChargeStation")
    var chargeStation: ChargeStation? = null
)


data class ScoketStatusList(
    var spot_abrv: String? = null,
    var spot_status: String? = null
)