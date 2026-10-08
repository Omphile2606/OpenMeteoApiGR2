package com.example.openmeteoapigr2

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val viewForecastButton =
            findViewById<Button>(R.id.viewForecastButton)

        val forecastContainer =
            findViewById<View>(R.id.forecastContainer)

        viewForecastButton.setOnClickListener {

            forecastContainer.visibility = View.VISIBLE

            viewForecastButton.visibility = View.GONE

            val transaction: FragmentTransaction =
                supportFragmentManager.beginTransaction()

            transaction.replace(
                R.id.forecastContainer,
                ForecastFragment()
            )

            transaction.commit()
        }
    }
}