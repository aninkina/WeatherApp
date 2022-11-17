package dev.fstudio.weather.new_api

import dev.fstudio.weather.api.get.Forecast
import dev.fstudio.weather.api.get.FullForecast
import io.ktor.client.*
import io.ktor.client.request.*

class OpenWeatherApiImpl(
    private val client: HttpClient
) : OpenWeatherApi {
    override suspend fun getFullForecast(lat: Double, lon: Double): FullForecast {
        return try {
            client.get {
                url(HttpRoutes.GET_FULL_FORECAST)
                parameter("lat", lat)
                parameter("lon", lon)
            }
        } catch (e: Exception){
            error(e.message.toString())
        }
    }

    override suspend fun getForecast(lat: Double, lon: Double): Forecast {
        return try {
            client.get {
                url(HttpRoutes.GET_FORECAST)
                parameter("lat", lat)
                parameter("lon", lon)
            }
        } catch (e: Exception){
            error(e.message.toString())
        }
    }
}