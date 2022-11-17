package dev.fstudio.weather.ui.fragment.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.fstudio.weather.api.get.model.DataList
import dev.fstudio.weather.databinding.ItemHourlyWeatherBinding
import dev.fstudio.weather.util.ConverterUtil


class HourlyWeatherAdapter(private val dataSet: List<DataList>) :
    RecyclerView.Adapter<HourlyWeatherAdapter.WeatherViewHolder>() {

    class WeatherViewHolder(private val item: ItemHourlyWeatherBinding) :
        RecyclerView.ViewHolder(item.root) {
        fun bind(dt: Long, humidity: Int, icon: String, temp: Double, speed: Double) {
            item.tvDaytime.text = ConverterUtil.millisecondsToDate("HH:MM", dt)
            item.tvHumidityValue.text = ConverterUtil.getPercentage(humidity)
            item.tvTempDay.text = ConverterUtil.kelvinToCelsius(temp)
            item.tvSpeed.text = ConverterUtil.getSpeedInMS(speed)


            val weatherConditionIconUrl = "http://openweathermap.org/img/w/${icon}.png"
            Glide.with(itemView.context).load(weatherConditionIconUrl).override(120)
                .into(item.ivClouds)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(
            ItemHourlyWeatherBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false

            )
        )
    }

    override fun onBindViewHolder(weatherViewHolder: WeatherViewHolder, position: Int) {
        val item = dataSet[position]
        weatherViewHolder.bind(
            item.dt,
            item.main.humidity,
            item.weather[0].icon,
            item.main.temp,
            item.wind.speed
        )
    }

    override fun getItemCount() = dataSet.size

}

