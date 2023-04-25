package com.example.valo_lineups.data.model.response

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class MapsResponse (

    @SerialName("status")
    val status: Int,

    @SerialName("data")
    val data: Data,
) {

    @kotlinx.serialization.Serializable
    data class Data(
        @SerialName("displayName") val displayName: String,
        @SerialName("displayIcon") val displayIcon: String
    )

}


