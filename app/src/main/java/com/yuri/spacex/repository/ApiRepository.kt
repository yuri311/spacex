package com.yuri.spacex.repository

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.yuri.spacex.dataobjects.Launch
import com.yuri.spacex.dataobjects.Rocket
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val SPACEX_BASE_ENDPOINT = "https://api.spacexdata.com/v4/"


class ApiRepository {

    companion object {

        suspend fun getAllLaunches(): List<Launch>? {
            val gson: Gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

            val retrofit = Retrofit.Builder()
                .baseUrl(SPACEX_BASE_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            val service = retrofit.create(ApiInterface::class.java)
            val response = service.getAllLaunches()
                if (response.isSuccessful) {
                    return response.body()
                }
            return emptyList()
        }

        suspend fun getOneLaunch(launchId: String): Launch? {
            val gson: Gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

            val retrofit = Retrofit.Builder()
                .baseUrl(SPACEX_BASE_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            val service = retrofit.create(ApiInterface::class.java)
            val response = service.getOneLaunch(launchId)

            if (response.isSuccessful) {
                return response.body()
            }
            return null
        }

        suspend fun getOneRocket(launchId: String): Rocket? {
            val gson: Gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

            val retrofit = Retrofit.Builder()
                .baseUrl(SPACEX_BASE_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            val service = retrofit.create(ApiInterface::class.java)
            val response = service.getOneRocket(launchId)

            if (response.isSuccessful) {
                return response.body()
            }
            return null
        }
    }
}
