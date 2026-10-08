package com.example.openmeteoapigr2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class ForecastFragment : Fragment() {

    private lateinit var forecastRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(
            R.layout.fragment_forecast,
            container,
            false
        )
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        forecastRecyclerView =
            view.findViewById(R.id.forecastRecyclerView)

        forecastRecyclerView.layoutManager =
            LinearLayoutManager(requireContext())

        loadForecast()
    }

    private fun loadForecast() {

        viewLifecycleOwner.lifecycleScope.launch {

            try {

                val response =
                    apiObject.retrofit.getWeather(
                        -25.7479,
                        28.2293
                    )

                val forecasts: List<WeatherForecast> =
                    response.hourly.time.indices.map { index ->

                        val dateTime =
                            response.hourly.time[index]

                        val date =
                            dateTime.substringBefore("T")

                        val time =
                            dateTime.substringAfter("T")

                        WeatherForecast(
                            temperature =
                                response.hourly
                                    .temperature_2m[index],

                            date = date,

                            time = time,

                            weatherCode =
                                response.hourly
                                    .weather_code[index]
                        )
                    }

                forecastRecyclerView.adapter =
                    ForecastAdapter(forecasts)

            } catch (e: Exception) {

                e.printStackTrace()
            }
        }
    }
}