package com.juanisaac.huertas.trujillo.librodecampo.fragment

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.juanisaac.huertas.trujillo.librodecampo.R
import com.juanisaac.huertas.trujillo.librodecampo.api.*
import com.juanisaac.huertas.trujillo.librodecampo.extensions.action
import com.juanisaac.huertas.trujillo.librodecampo.extensions.displaySnak
import com.juanisaac.huertas.trujillo.librodecampo.model.CurrentWeather
import com.juanisaac.huertas.trujillo.librodecampo.model.Day
import com.juanisaac.huertas.trujillo.librodecampo.model.Hour
import com.juanisaac.huertas.trujillo.librodecampo.views.DailyWeather
import com.juanisaac.huertas.trujillo.librodecampo.views.HourlyWeather
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class WeatherFragment : Fragment() {

    val TAG = WeatherFragment::class.java.simpleName
    var days: ArrayList<Day> = ArrayList()
    var hours: ArrayList<Hour> = ArrayList()
    private lateinit var fusedLocationClient: FusedLocationProviderClient


    //vva del arzobispo
    var latitud: Double = 38.1695754
    var longitud: Double = -3.0064317000000074
    var url_weather: String = ""
    var url_city: String = ""

    companion object {
        val DAILY_WEATHER = "DAILY_WEATHER"
        val HOURLY_WEATHER = "HOURLY_WEATHER"
        val MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 0
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onStart() {
        super.onStart()
        btnDailyWeather.setOnClickListener {
            val i = Intent(context!!, DailyWeather::class.java)
            i.putParcelableArrayListExtra(DAILY_WEATHER, days)
            startActivity(i)
        }
        btnHourlyWeather.setOnClickListener {
            val i = Intent(context!!, HourlyWeather::class.java)
            i.putParcelableArrayListExtra(HOURLY_WEATHER, hours)
            startActivity(i)
        }
        if (ContextCompat.checkSelfPermission(context!!, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity!!)

            Log.e(TAG, "${fusedLocationClient.lastLocation.isSuccessful}")
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    Log.e("error", "Encuentra localizacion");
                    latitud = location.latitude
                    longitud = location.longitude
                    getWeather()
                    getCity()
                    Log.d(TAG, "Despues: $latitud   $longitud")
                } else {
                    getWeather()
                    getCity()
                }
            }
            fusedLocationClient.locationAvailability.addOnSuccessListener {
                fusedLocationClient.lastLocation.addOnSuccessListener { location -> }
                getWeather()
                getCity()
            }
            fusedLocationClient.lastLocation.addOnFailureListener {
                getWeather()
                getCity()
            }

        } else {
            solicitarPermisoUbicacion()
        }

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        btnDailyWeather.setOnClickListener {
            val i = Intent(context!!, DailyWeather::class.java)
            i.putParcelableArrayListExtra(DAILY_WEATHER, days)
            startActivity(i)
        }
        btnHourlyWeather.setOnClickListener {
            val i = Intent(context!!, HourlyWeather::class.java)
            i.putParcelableArrayListExtra(HOURLY_WEATHER, hours)
            startActivity(i)
        }
    }

    fun solicitarPermisoUbicacion() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity!!,
                        Manifest.permission.ACCESS_FINE_LOCATION)) {

        } else {
            ActivityCompat.requestPermissions(activity!!,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                }
                return
            }
        }
    }

    private fun getWeather() {
        Log.d(TAG, "Latitud y longitud en el tiempo $latitud   $longitud")
        url_weather = "$DARK_SKY_URL/$API_KEY/$latitud,$longitud?lang=es&units=si"
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context!!)

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, url_weather,
                Response.Listener<String> {

                    val responseJson = JSONObject(it)
                    val currentWeather = getCurrenWeatherFromJson(responseJson)
                    days = getDayWeatherFromJson(responseJson)
                    hours = getHourlyFromJson(responseJson)
                    buildCurrentWeatherUI(currentWeather)

                },
                Response.ErrorListener {

                    displayErrorMessage()
                })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }

    private fun displayErrorMessage() {
        weather_layout.displaySnak("No dispone de conexión a internet", Snackbar.LENGTH_INDEFINITE) {
            action("Intenta volver a conectarte") {
                getWeather()
            }
        }
    }

    fun buildCurrentWeatherUI(currentWeather: CurrentWeather) {
        val precipProbability = (currentWeather.precip * 100).toInt()
        tempTextView.text = "${currentWeather.temp} ºC"
        probTextView.text = "${precipProbability}%"
        dataTextView.text = currentWeather.summary
        icon.setImageDrawable(ResourcesCompat.getDrawable(resources, currentWeather.getIconResource(), null))
    }

    private fun getCity() {
        Log.d(TAG, "Latitud y longitud en la ciudad $latitud   $longitud")
        url_city = "$GEOCODE_URL?latlng=$latitud,$longitud&key=$API_GEOCODE"
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context!!)

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, url_city,
                Response.Listener<String> { response ->

                    val responseJson = JSONObject(response)
                    val city = getCity(responseJson)
                    cityTextView.setText(city)

                },
                Response.ErrorListener {
                    Snackbar.make(weather_layout, "No se encuentra la ciudad", Snackbar.LENGTH_INDEFINITE)
                            .show()
                })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }


}