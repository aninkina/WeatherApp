package dev.fstudio.weather.new_api

object HttpRoutes {
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5"
    const val GET_FORECAST = "$BASE_URL/forecast"
    const val GET_FULL_FORECAST = "$BASE_URL/onecall"
}