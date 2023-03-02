package com.example.appka_cvika_1.data.remote.service

import com.example.appka_cvika_1.data.model.response.RocketDetailResponse
import com.example.appka_cvika_1.data.model.response.RocketLaunchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpaceXAPIService {

    //"https://api.spacexdata.com/v3/launches" typu get
    @GET("launches")
    suspend fun fetchAllLaunches(
        @Query("launch_successful")
        wasLaunchSuccessful:Boolean
    ):List<RocketLaunchResponse>

    //"https://api.spacexdata.com/v3/rockets/{rocketID}" typu get
    @GET("rockets/{rocketId}")
    suspend fun fetchRocketDetail(
        @Path("rocketId")
        rocketId:String
    ):RocketDetailResponse?

}