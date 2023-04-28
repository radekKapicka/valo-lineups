package com.example.valo_lineups.data.database.model

data class Maps(
    var image: String = "",
    var title: String = "",
    var uuid: String = "",
    var agent: Agent = Agent(),
)
{
    data class Agent(
        var name: String = "",
        var uuid: String = "",
        var mapUuid: String = "",
        var image: String = "",
    )
}

