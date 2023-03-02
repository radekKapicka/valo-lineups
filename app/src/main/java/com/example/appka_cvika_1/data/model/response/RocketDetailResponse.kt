package com.example.appka_cvika_1.data.model.response

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class RocketDetailResponse (
    @SerialName("id")
    val id: Long,

    @SerialName("rocket_name")
    val name: String,

    @SerialName("description")
    val description: String,

    @SerialName("active")
    val isActive: Boolean,

    @SerialName("stages")
    val stagesCount: Int,

    @SerialName("wikipedia")
    val wikipediaLink: String,

    )