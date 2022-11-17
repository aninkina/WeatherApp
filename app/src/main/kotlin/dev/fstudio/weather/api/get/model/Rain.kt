package dev.fstudio.weather.api.get.model

import kotlinx.serialization.Serializable

@Serializable
data class Rain(
    val `3h`: Double
)