package dev.fstudio.weather.api.get.model_onecall

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Hourly(
    val clouds: Int,
    @SerialName("dew_point")
    val dewPoint: Double,
    val dt: Int,
    @SerialName("feels_like")
    val feelsLike: Double,
    val humidity: Double,
    val pop: Double,
    val pressure: Double,
    val temp: Double,
    val uvi: Double,
    val visibility: Int,
    val weather: List<Weather>,
    val rain: Rain? = null,
    val snow: Snow? = null,
    @SerialName("wind_deg")
    val windDeg: Int,
    @SerialName("wind_speed")
    val windSpeed: Double,
    @SerialName("wind_gust")
    val windGust: Double
)