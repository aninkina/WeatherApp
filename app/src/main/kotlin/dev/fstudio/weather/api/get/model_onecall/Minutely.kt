package dev.fstudio.weather.api.get.model_onecall

import kotlinx.serialization.Serializable

@Serializable
data class Minutely(
    val dt: Int,
    val precipitation: Double
)