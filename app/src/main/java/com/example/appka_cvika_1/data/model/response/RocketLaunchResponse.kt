package com.example.appka_cvika_1.data.model.response

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class RocketLaunchResponse (

    @SerialName("flight_number")
    val flightNumber: Int,

    @SerialName("mission_name")
    val missionName: String,

    @SerialName("rocket")
    val rocket: Rocket,

)

@kotlinx.serialization.Serializable
data class Rocket(
    @SerialName("rocket_id")
    val id: String,

    @SerialName("rocket_name")
    val rocketName: String,
)

