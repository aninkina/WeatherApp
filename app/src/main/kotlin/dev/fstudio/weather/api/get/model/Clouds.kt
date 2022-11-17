package dev.fstudio.weather.api.get.model

import kotlinx.serialization.Serializable

@Serializable
data class Clouds(
    val all: Int
)