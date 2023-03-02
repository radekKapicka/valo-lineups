package com.example.appka_cvika_1.data.repository

import com.example.appka_cvika_1.data.model.response.RocketDetailResponse
import com.example.appka_cvika_1.data.model.response.RocketLaunchResponse
import com.example.appka_cvika_1.data.remote.service.SpaceXAPIService

class SpeceXRepository(
    private val spaceXAPIService: SpaceXAPIService
): BaseRepository() {

    //kotlin zápis
    suspend fun fetchAllSuccessfulLaunches() = spaceXAPIService.fetchAllLaunches(wasLaunchSuccessful = true)

    //java zápis
    suspend fun fetchRocketDetail(rocketId:String):RocketDetailResponse?{
        return spaceXAPIService.fetchRocketDetail(rocketId)
    }

}