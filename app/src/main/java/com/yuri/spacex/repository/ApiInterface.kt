package com.yuri.spacex.repository

import com.yuri.spacex.dataobjects.Launch
import com.yuri.spacex.dataobjects.Rocket
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("launches/")
    suspend fun getAllLaunches(): Response<List<Launch>>

    @GET("launches/{id}")
    suspend fun getOneLaunch(@Path("id") id: String): Response<Launch>

    @GET("rockets/{id}")
    suspend fun getOneRocket(@Path("id") id: String): Response<Rocket>
}
