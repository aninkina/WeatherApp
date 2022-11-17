package dev.fstudio.weather.util

import java.text.SimpleDateFormat
import java.util.*

object ConverterUtil {

    fun millisecondsToDate(dateFormat: String, dateTime: Long):String{
       return SimpleDateFormat(dateFormat, Locale.getDefault()).format(dateTime * 1000)
    }

    fun kelvinToCelsius(temp: Double) : String{
        return "%.1f ℃".format(temp - 273.15)
    }

    fun getPercentage(value: Int): String {
        return "%d %%".format(value)
    }

    fun getSpeedInMS(value: Double) : String {
        return "%.1f m/s".format(value)
    }
}