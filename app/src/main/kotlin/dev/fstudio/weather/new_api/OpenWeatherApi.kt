package dev.fstudio.weather.new_api

import dev.fstudio.weather.api.get.Forecast
import dev.fstudio.weather.api.get.FullForecast
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*

interface OpenWeatherApi {
    suspend fun getFullForecast(lat: Double, lon: Double): FullForecast

    suspend fun getForecast(lat: Double, lon: Double): Forecast

    companion object {
        fun create() : OpenWeatherApi {
            return OpenWeatherApiImpl(
                client = HttpClient(Android){
                    install(JsonFeature) {
                        serializer = KotlinxSerializer()
                    }
                    defaultRequest {
                        parameter("appid", "f747c7257813a7127a2d9de58c58ae94")
                        contentType(ContentType.Application.Json)
                    }
                }
            )
        }
    }
}