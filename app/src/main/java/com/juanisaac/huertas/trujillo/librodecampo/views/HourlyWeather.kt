package com.juanisaac.huertas.trujillo.librodecampo.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.juanisaac.huertas.trujillo.librodecampo.R
import com.juanisaac.huertas.trujillo.librodecampo.adapter.HourAdapter
import com.juanisaac.huertas.trujillo.librodecampo.fragment.WeatherFragment.Companion.HOURLY_WEATHER
import com.juanisaac.huertas.trujillo.librodecampo.model.Hour
import kotlinx.android.synthetic.main.activity_hourly_weather.*

class HourlyWeather : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hourly_weather)

        recyclerHour.layoutManager = LinearLayoutManager(this)

        intent.let {
            var hours:ArrayList<Hour> = it.getParcelableArrayListExtra(HOURLY_WEATHER)
            recyclerHour.adapter = HourAdapter(hours)
        }
    }
}
