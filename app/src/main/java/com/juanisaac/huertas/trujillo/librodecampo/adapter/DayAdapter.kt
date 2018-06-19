package com.juanisaac.huertas.trujillo.librodecampo.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.juanisaac.huertas.trujillo.librodecampo.R
import com.juanisaac.huertas.trujillo.librodecampo.extensions.inflate
import com.juanisaac.huertas.trujillo.librodecampo.model.Day

class DayAdapter(val context: Context, val dataSource: ArrayList<Day>) : BaseAdapter() {
    override fun getView(position: Int, currentView: View?, parentView: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder

        if (currentView == null) {
            view = parentView.inflate(R.layout.daily_item)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            viewHolder = currentView.tag as ViewHolder
            view = currentView
        }
        val currentDay = dataSource[position]
        viewHolder.apply {
            dayTextView.text = swipeDay(currentDay.getFormatDay())
            minTempTextView.text = "Min: ${currentDay.temperatureMin.toInt()} ºC"
            maxTempTextView.text = "Max: ${currentDay.temperatureMax.toInt()} ºC"
            precipProbability.text = "Probabilidad lluvia: ${(currentDay.precipProbability *100).toInt() } %"
        }

        return view
    }

    fun swipeDay(day:String):String{
        when(day){
            "Saturday" -> return "Sabado"
            "Sunday" -> return "Domingo"
            "Monday" -> return "Lunes"
            "Tuesday" -> return "Martes"
            "Wednesday" -> return "Miércoles"
            "Thursday" -> return "Jueves"
            "Friday" -> return "Viernes"
            else -> return ""
        }
    }


    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return dataSource.size
    }

    private class ViewHolder(view: View) {
        val dayTextView: TextView = view.findViewById(R.id.day)
        val minTempTextView: TextView = view.findViewById(R.id.min_temp)
        val maxTempTextView: TextView = view.findViewById(R.id.max_temp)
        val precipProbability: TextView = view.findViewById(R.id.precip_probability)

    }
}