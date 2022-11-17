package dev.fstudio.weather.api.get.model_onecall

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Current(
    val clouds: Int,
    @SerialName("dew_point")
    val dewPoint: Double,
    val dt: Int,
    @SerialName("feels_like")
    val feelsLike: Double,
    val humidity: Int,
    val pressure: Int,
    val rain: Rain? = null,
    val snow: Snow? = null,
    val sunrise: Int,
    val sunset: Int,
    val temp: Double,
    val uvi: Double,
    val visibility: Int,
    val weather: List<Weather>,
    @SerialName("wind_deg")
    val windDeg: Int,
    @SerialName("wind_speed")
    val windSpeed: Double,
    @SerialName("wind_gust")
    val windGust: Double? = null
)