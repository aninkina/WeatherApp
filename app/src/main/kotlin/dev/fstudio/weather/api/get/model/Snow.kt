package dev.fstudio.weather.api.get.model

import kotlinx.serialization.Serializable

@Serializable
data class Snow(
    val `3h`: Double
)