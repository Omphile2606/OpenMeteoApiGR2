package com.example.openmeteoapigr2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load

class ForecastAdapter(
    private val forecasts: List<WeatherForecast>
) : RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    class ForecastViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        val weatherIcon: ImageView =
            itemView.findViewById(R.id.weatherIcon)

        val temperatureText: TextView =
            itemView.findViewById(R.id.temperatureText)

        val dateText: TextView =
            itemView.findViewById(R.id.dateText)

        val timeText: TextView =
            itemView.findViewById(R.id.timeText)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ForecastViewHolder {

        val view = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.item_forecast,
                parent,
                false
            )

        return ForecastViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ForecastViewHolder,
        position: Int
    ) {

        val forecast = forecasts[position]

        holder.temperatureText.text =
            "${forecast.temperature} °C"

        holder.dateText.text =
            forecast.date

        holder.timeText.text =
            forecast.time

        val iconUrl =
            getWeatherIconUrl(forecast.weatherCode)

        holder.weatherIcon.load(iconUrl)
    }

    override fun getItemCount(): Int {
        return forecasts.size
    }

    private fun getWeatherIconUrl(
        weatherCode: Int
    ): String {

        return when (weatherCode) {

            0 ->
                "https://openweathermap.org/img/wn/01d@2x.png"

            1, 2 ->
                "https://openweathermap.org/img/wn/02d@2x.png"

            3 ->
                "https://openweathermap.org/img/wn/04d@2x.png"

            45, 48 ->
                "https://openweathermap.org/img/wn/50d@2x.png"

            51, 53, 55, 56, 57 ->
                "https://openweathermap.org/img/wn/09d@2x.png"

            61, 63, 65, 66, 67 ->
                "https://openweathermap.org/img/wn/10d@2x.png"

            71, 73, 75, 77 ->
                "https://openweathermap.org/img/wn/13d@2x.png"

            80, 81, 82 ->
                "https://openweathermap.org/img/wn/09d@2x.png"

            95, 96, 99 ->
                "https://openweathermap.org/img/wn/11d@2x.png"

            else ->
                "https://openweathermap.org/img/wn/01d@2x.png"
        }
    }
}