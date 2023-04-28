package com.example.valo_lineups.data.repository

import com.example.valo_lineups.data.remote.service.ValoAPIService

class ValoRepository(
    private val valoAPIService: ValoAPIService
): BaseRepository() {

    //kotlin zápis
    suspend fun fetchAgentDetail(agentId:String) =
        valoAPIService.fetchAgentDetail(agentId = agentId)


}