package com.juanisaac.huertas.trujillo.librodecampo.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.juanisaac.huertas.trujillo.librodecampo.R
import com.juanisaac.huertas.trujillo.librodecampo.R.id.emptyTextView
import com.juanisaac.huertas.trujillo.librodecampo.adapter.DayAdapter
import com.juanisaac.huertas.trujillo.librodecampo.fragment.WeatherFragment
import com.juanisaac.huertas.trujillo.librodecampo.model.Day
import kotlinx.android.synthetic.main.activity_daily_weather.*

class DailyWeather : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_weather)

        intent.let {

            val days:ArrayList<Day> = it.getParcelableArrayListExtra(WeatherFragment.DAILY_WEATHER)
            val baseAdapter = DayAdapter(this, days)
            list_daily.adapter = baseAdapter

            list_daily.emptyView = emptyTextView
        }

    }
}
