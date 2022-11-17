package dev.fstudio.weather.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dev.fstudio.weather.api.get.model.DataList
import java.text.SimpleDateFormat
import java.util.*


object MiscUtil {

    fun getDefaultSharedPreference(context: Context): SharedPreferences {
        return context.getSharedPreferences("key_event", MODE_PRIVATE)
    }

    fun getSubString(preferences: SharedPreferences): String {
        return StringBuilder()
            .append("Lat: ")
            .append(preferences.getFloat("lat", 0F).toDouble())
            .append(" | ")
            .append("Lon: ")
            .append(preferences.getFloat("lon", 0F).toDouble())
            .toString()
    }

    fun checkIfFirstEntry(preferences: SharedPreferences): Boolean {
        return if (preferences.getBoolean("first", true)) {
            preferences.edit().putBoolean("first", false).apply()
            true;
        } else {
            false;
        }
    }

    fun getCity(preferences: SharedPreferences): String {
        return StringBuilder()
            .append("City: ").append(preferences.getString("city", "null")).toString();
    }

    fun getSubList(list: List<DataList>, pos: Int): List<DataList> {
        val formatter = SimpleDateFormat("EEEE")
        val dayOfWeek = formatter.format(addDays(Date(), pos))

        val newList = mutableListOf<DataList>()
        for (el in list) {
            val dayOfWeekEl = formatter.format(el.dt * 1000)
            if (dayOfWeek == dayOfWeekEl) {
                newList.add(el)
            } else if (newList.isNotEmpty()) {
                break
            }
        }

        return newList
    }

    private fun addDays(date: Date, days: Int): Date {
        val cal = Calendar.getInstance()
        cal.time = date
        cal.add(Calendar.DATE, days) //minus number would decrement the days
        return cal.time
    }
}