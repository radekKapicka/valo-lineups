package com.example.valo_lineups.data.remote.service

import com.example.valo_lineups.data.model.response.AgentDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ValoAPIService {

    @GET("{uuid}")
    suspend fun fetchAgentDetail(@Path("uuid") agentId: String): AgentDetailResponse


}