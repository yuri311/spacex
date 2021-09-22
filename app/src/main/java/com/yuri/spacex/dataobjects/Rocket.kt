package com.yuri.spacex.dataobjects

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Rocket(

    @Expose
    @SerializedName("id")
    var launchId: String?,

    @Expose
    @SerializedName("name")
    var name: String?,

    @Expose
    @SerializedName("cost_per_launch")
    var cost: Long?
)
