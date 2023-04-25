package com.example.valo_lineups.data.repository

import com.example.valo_lineups.data.remote.service.ValoAPIService

class ValoRepository(
    private val valoAPIService: ValoAPIService
): BaseRepository() {

    //kotlin zápis
    suspend fun fetchAllMaps() = valoAPIService.fetchAllMaps()

    //java zápis
    /*suspend fun fetchRocketDetail(rocketId:String):RocketDetailResponse?{
        return spaceXAPIService.fetchRocketDetail(rocketId)
    }.*/

}