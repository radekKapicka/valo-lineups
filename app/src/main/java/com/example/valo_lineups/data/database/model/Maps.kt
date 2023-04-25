package com.example.valo_lineups.data.database.model

data class Maps(
    var image: String = "null",
    var title: String = "null",
    var uuid: String = "null",
    var agent: Agent = Agent(),
)
{
    data class Agent(
        var name: String = "null",
    )
}

