package com.example.valo_lineups.data.remote.service

import com.example.valo_lineups.data.model.response.MapsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ValoAPIService {

    //"https://api.spacexdata.com/v3/launches" typu get
    @GET("maps")
    suspend fun fetchAllMaps(): List<MapsResponse>


}