package com.yuri.spacex.dataobjects

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Launch(

    @Expose
    @SerializedName("id")
    var launchId: String?,

    @Expose
    @SerializedName("rocket")
    var rocket: String?,

    @Expose
    @SerializedName("name")
    var name: String?,

    @Expose
    @SerializedName("details")
    var details: String?,

    @Expose
    @SerializedName("flight_number")
    var flightNumber: String?,

    @Expose
    @SerializedName("date_utc")
    var dateUtc: String?,

    @Expose
    @SerializedName("date_unix")
    var dateUnix: String?,

    @Expose
    @SerializedName("success")
    var success: Boolean
)
