package dev.fstudio.weather.ui.fragment.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.fstudio.weather.api.get.model_onecall.Daily
import dev.fstudio.weather.databinding.ItemDailyWeatherBinding
import dev.fstudio.weather.util.ConverterUtil


class DailyWeatherAdapter(private val dataSet: List<Daily>) :
    RecyclerView.Adapter<DailyWeatherAdapter.WeatherByDayViewHolder>() {

    class WeatherByDayViewHolder(private val item: ItemDailyWeatherBinding) :
        RecyclerView.ViewHolder(item.root) {
        fun bind(
            dt: Long,
            tempDay: Double,
            tempNight: Double,
            pressure: Int,
            humidity: Int,
            speed: Double,
            icon: String
        ) {
            item.tvDate.text = ConverterUtil.millisecondsToDate("dd/MM", dt)
            item.tvDayOfWeek.text = ConverterUtil.millisecondsToDate("EEEE", dt)
            item.tvTempDay.text = ConverterUtil.kelvinToCelsius(tempDay)
            item.tvTempNight.text = ConverterUtil.kelvinToCelsius(tempNight)
            item.tvPressureValue.text = pressure.toString()
            item.tvHumidityValue.text = humidity.toString()
            item.tvWindValue.text = speed.toString()

            val weatherConditionIconUrl = "http://openweathermap.org/img/w/${icon}.png"
            Glide.with(itemView.context).load(weatherConditionIconUrl).override(200)
                .into(item.ivClouds)

        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): WeatherByDayViewHolder {
        return WeatherByDayViewHolder(
            ItemDailyWeatherBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false

            )
        )
    }

    override fun onBindViewHolder(weatherViewHolder: WeatherByDayViewHolder, position: Int) {
        val item = dataSet[position]
        weatherViewHolder.bind(
            item.dt,
            item.temp.day,
            item.temp.night,
            item.pressure,
            item.humidity,
            item.windSpeed,
            item.weather[0].icon
        )


    }

    override fun getItemCount() = dataSet.size


}

