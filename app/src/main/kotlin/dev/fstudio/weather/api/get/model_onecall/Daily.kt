package dev.fstudio.weather.api.get.model_onecall

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Daily(
    val clouds: Int,
    @SerialName("dew_point")
    val dewPoint: Double,
    val dt: Long,
    @SerialName("feels_like")
    val feelsLike: FeelsLike,
    val humidity: Int,
    @SerialName("moon_phase")
    val moonPhase: Double,
    val moonrise: Int,
    val moonset: Int,
    val pop: Double,
    val pressure: Int,
    val rain: Double? = 0.0,
    val snow: Double? = 0.0,
    val sunrise: Int,
    val sunset: Int,
    val temp: Temp,
    val uvi: Double,
    val weather: List<Weather>,
    @SerialName("wind_deg")
    val windDeg: Int,
    @SerialName("wind_speed")
    val windSpeed: Double,
    @SerialName("wind_gust")
    val windGust: Double
)