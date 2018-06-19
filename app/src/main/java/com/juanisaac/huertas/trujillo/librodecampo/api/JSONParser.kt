package com.juanisaac.huertas.trujillo.librodecampo.api

import com.juanisaac.huertas.trujillo.librodecampo.model.CurrentWeather
import com.juanisaac.huertas.trujillo.librodecampo.model.Day
import com.juanisaac.huertas.trujillo.librodecampo.extensions.iterator
import com.juanisaac.huertas.trujillo.librodecampo.model.Hour
import org.json.JSONObject

fun getCurrenWeatherFromJson(response: JSONObject): CurrentWeather {

    val currentJson = response.getJSONObject(currently)

    with(currentJson) {
        val currentWeather = CurrentWeather(getString(icon),
                getString(summary),
                getDouble(temperature),
                getDouble(precipProbability))
        return currentWeather
    }
}

fun getDayWeatherFromJson(response: JSONObject): ArrayList<Day> {
    val currentJson = response.getJSONObject(daily)
    val dayJson = currentJson.getJSONArray(data)
    val days = ArrayList<Day>()
    for (day in dayJson) {
        val time = day.getLong(time)
        val minTemp = day.getDouble(temperatureMin)
        val maxTemp = day.getDouble(temperatureMax)
        val precipProbability = day.getDouble(precipProbability)
        days.add(Day(time, minTemp, maxTemp, precipProbability))
    }
    return days
}

fun getCity(response: JSONObject): String {
    val objectJson = response.getJSONArray("results")
    val address = objectJson.getJSONObject(1)
    val data = address.getJSONArray("address_components")
    val detalle = data.getJSONObject(1)
    val ciudad = detalle.get("long_name").toString()
    return ciudad
}

fun getHourlyFromJson(response: JSONObject): ArrayList<Hour> {

    val currentJson = response.getJSONObject(hourly)
    val timeZoneFromJson: String = response.getString(timeZone)
    val hourJson = currentJson.getJSONArray(data)
    val hours = ArrayList<Hour>()
    for (hour in hourJson) {
        val time = hour.getLong(time)
        val minTemp = hour.getDouble(temperature)
        val precipProbability = hour.getDouble(precipProbability)
        hours.add(Hour(time, minTemp, precipProbability, timeZoneFromJson))
    }
    return hours
}