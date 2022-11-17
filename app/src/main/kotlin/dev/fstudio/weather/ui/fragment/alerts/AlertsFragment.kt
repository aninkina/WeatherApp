package dev.fstudio.weather.ui.fragment.alerts

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dev.fstudio.weather.AlarmReceiver
import dev.fstudio.weather.R
import dev.fstudio.weather.databinding.FragmentAlertsBinding
import dev.fstudio.weather.new_api.OpenWeatherApi
import dev.fstudio.weather.util.MiscUtil.getDefaultSharedPreference
import kotlinx.coroutines.launch
import java.util.*

class AlertsFragment : Fragment(R.layout.fragment_alerts) {

    private lateinit var binding: FragmentAlertsBinding
    private val newService = OpenWeatherApi.create()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlertsBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        activity?.let { setAlarm(it) }
    }

    private fun setupRecyclerView() = binding.rvAlertsItems.apply {
        lifecycleScope.launch {
            kotlin.runCatching {
                newService.getFullForecast(
                    getDefaultSharedPreference(context).getFloat("lat", 0F).toDouble(),
                    getDefaultSharedPreference(context).getFloat("lon", 0F).toDouble()
                )
            }.onSuccess {
                adapter = AlertsAdapter(it.alerts)
            }.onFailure {
                error(it.message.toString())
            }
        }
        layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setAlarm(context: Context) {
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent: PendingIntent? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_MUTABLE)
        } else {
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        }
        val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        am.setRepeating(
            AlarmManager.RTC_WAKEUP,
            Calendar.getInstance().timeInMillis,
            (1000 * 1).toLong(),
            pendingIntent
        )
    }
}