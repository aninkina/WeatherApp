package dev.fstudio.weather.api.get


import dev.fstudio.weather.api.get.model.City
import dev.fstudio.weather.api.get.model.DataList
import kotlinx.serialization.Serializable
@Serializable
data class Forecast(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<DataList>,
    val message: Int
)