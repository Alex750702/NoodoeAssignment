package com.studio.noodoeassignment.data

import com.google.gson.annotations.SerializedName

data class ParkingLotInfo(
    @SerializedName("UPDATETIME")
    var uPDATETIME: String? = null,
    @SerializedName("park")
    var parkInfo: ArrayList<ParkInfo>? = null
)

data class EntranceCoord(
    @SerializedName("EntrancecoordInfo")
    var entrancecoordInfo: ArrayList<EntrancecoordInfo>? = null
)

data class EntrancecoordInfo(
    @SerializedName("Xcod")
    var xcod: String? = null,

    @SerializedName("Ycod")
    var ycod: String? = null,

    @SerializedName("Address")
    var address: String? = null
)

data class FareInfo(
    @SerializedName("WorkingDay")
    var workingDay: ArrayList<WorkingDay>? = null,

    @SerializedName("Holiday")
    var holiday: ArrayList<Holiday>? = null
)

data class Holiday(
    @SerializedName("Period")
    var period: String? = null,

    @SerializedName("Fare")
    var fare: String? = null
)

data class ParkInfo(
    var id: String? = null,
    var area: String? = null,
    var name: String? = null,
    var type: String? = null,
    var type2: String? = null,
    var summary: String? = null,
    var address: String? = null,
    var tel: String? = null,
    var payex: String? = null,
    var serviceTime: String? = null,
    var tw97x: String? = null,
    var tw97y: String? = null,
    var totalcar: String? = null,
    var totalmotor: String? = null,
    var totalbike: String? = null,
    var totalbus: String? = null,

    @SerializedName("Pregnancy_First")
    var pregnancy_First: String? = null,

    @SerializedName("Handicap_First")
    var handicap_First: String? = null,

    @SerializedName("Taxi_OneHR_Free")
    var taxi_OneHR_Free: String? = null,

    @SerializedName("AED_Equipment")
    var aED_Equipment: String? = null,

    @SerializedName("CellSignal_Enhancement")
    var cellSignal_Enhancement: String? = null,

    @SerializedName("Accessibility_Elevator")
    var accessibility_Elevator: String? = null,

    @SerializedName("Phone_Charge")
    var phone_Charge: String? = null,

    @SerializedName("Child_Pickup_Area")
    var child_Pickup_Area: String? = null,

    @SerializedName("FareInfo")
    var fareInfo: FareInfo? = null,

    @SerializedName("EntranceCoord")
    var entranceCoord: EntranceCoord? = null,
    var totallargemotor: String? = null,

    @SerializedName("ChargingStation")
    var chargingStation: String? = null,
)


data class WorkingDay(
    @SerializedName("Period")
    var period: String? = null,

    @SerializedName("Fare")
    var fare: String? = null
)