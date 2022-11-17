package dev.fstudio.weather.api.get.model_onecall

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Snow(
    @SerialName("1h")
    val h: Double
)