package com.juanisaac.huertas.trujillo.librodecampo.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.juanisaac.huertas.trujillo.librodecampo.R
import com.juanisaac.huertas.trujillo.librodecampo.extensions.inflate
import com.juanisaac.huertas.trujillo.librodecampo.model.Hour
import kotlinx.android.synthetic.main.hourly_item.view.*

class HourAdapter(val datasource:ArrayList<Hour>):RecyclerView.Adapter<HourAdapter.HourViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourViewHolder
            = HourViewHolder(parent.inflate(R.layout.hourly_item))


    override fun getItemCount(): Int = datasource.size

    override fun onBindViewHolder(holder: HourViewHolder, position: Int){
        return holder.bind(datasource[position])
    }


    class HourViewHolder(hourlyItem: View): RecyclerView.ViewHolder(hourlyItem){

        fun bind(hour:Hour) = with(itemView){
            hourText.text = "Hora: " +hour.getFormatDay()
            hourTempText.text = "Temperatura: ${hour.temp.toInt()}ºC"
            hourProbText.text = "Probabilidad lluvia: ${((hour.precip)* 100).toInt()} %"

        }
    }
}