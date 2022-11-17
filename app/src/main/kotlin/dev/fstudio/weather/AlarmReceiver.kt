package dev.fstudio.weather

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import dev.fstudio.weather.api.get.model_onecall.Alert
import dev.fstudio.weather.new_api.OpenWeatherApi
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class AlarmReceiver : BroadcastReceiver(), KoinComponent {

    private val newService = OpenWeatherApi.create()

    private val sharedPreferences: SharedPreferences by inject()

    private var job: Job? = null

    override fun onReceive(context: Context, intent: Intent) {
        createNotificationChannel(context)

        job = CoroutineScope(Dispatchers.IO).launch {

            val response = newService.getFullForecast(
                sharedPreferences.getFloat("lat", 0F).toDouble(),
                sharedPreferences.getFloat("lon", 0F).toDouble()
            )

            withContext(Dispatchers.Main) {
                getNewAlerts(response.alerts, context)
            }
        }
    }

    private fun getNewAlerts(alerts: List<Alert>, context: Context) {
        var indexOfNewAlerts = 0
        for ((index, alert) in alerts.withIndex()) {
            if (alert.event == sharedPreferences.getString(KEY_EVENT, "")) {
                indexOfNewAlerts = index
            }
        }

        for ((index, alert) in alerts.withIndex()) {
            if (index > indexOfNewAlerts) {
                if (alert.description.isNotEmpty()) {
                    setNotification(context, alert, NOTIFICATION_ID[index])
                }
            }
        }

        sharedPreferences.edit()
            .putString(KEY_EVENT, alerts.last().event)
            .apply()
    }


    private fun setNotification(context: Context, alert: Alert, notification_id: Int) {

        val builder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setContentTitle(alert.event)
            .setContentText(alert.description)
            .setSmallIcon(R.drawable.ic_notification)

        with(NotificationManagerCompat.from(context)) {
            notify(notification_id, builder.build())
        }
    }

    private fun createNotificationChannel(context: Context) {
        val descriptionText = "EMPTY DESCRIPTION"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            importance
        ).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }


    companion object {
        const val NOTIFICATION_CHANNEL_ID = "weather_channel"
        const val NOTIFICATION_CHANNEL_NAME = "Weather"
        val NOTIFICATION_ID = arrayOf(1, 2, 3, 4, 5, 6)
        const val KEY_EVENT = "key_event"
    }

}