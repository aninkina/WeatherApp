package dev.fstudio.weather.ui.fragment.alerts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.fstudio.weather.api.get.model_onecall.Alert
import dev.fstudio.weather.databinding.ItemAlertBinding

class AlertsAdapter(private val dataSet: List<Alert>) :
    RecyclerView.Adapter<AlertsAdapter.WeatherViewHolder>() {

    class WeatherViewHolder(private val item: ItemAlertBinding) :
        RecyclerView.ViewHolder(item.root) {
        fun bind(data: String) {
            item.tvAlertName.text = data
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(
            ItemAlertBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )
    }

    override fun onBindViewHolder(weatherViewHolder: WeatherViewHolder, position: Int) {
        weatherViewHolder.bind(dataSet[position].description)
    }

    override fun getItemCount() = dataSet.size

}