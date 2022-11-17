package dev.fstudio.weather.api.get

import dev.fstudio.weather.api.get.model_onecall.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FullForecast(
    val alerts: List<Alert> = emptyList(),
    val current: Current,
    val daily: List<Daily>,
    val hourly: List<Hourly>,
    val lat: Double,
    val lon: Double,
    val minutely: List<Minutely> = emptyList(),
    val timezone: String,
    @SerialName("timezone_offset")
    val timezoneOffset: Int
)