package com.juanisaac.huertas.trujillo.librodecampo.model

import android.os.Parcel
import android.os.Parcelable
import java.text.SimpleDateFormat
import java.util.*

data class Day (var time:Long, var temperatureMin:Double, var temperatureMax:Double, var precipProbability:Double ):Parcelable{

    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readDouble(),
            parcel.readDouble(),
            parcel.readDouble())


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(time)
        parcel.writeDouble(temperatureMin)
        parcel.writeDouble(temperatureMax)
        parcel.writeDouble(precipProbability)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Day> {
        override fun createFromParcel(parcel: Parcel): Day {
            return Day(parcel)
        }

        override fun newArray(size: Int): Array<Day?> {
            return arrayOfNulls(size)
        }
    }

    fun getFormatDay():String{

        val formater = SimpleDateFormat("EEEE", Locale.US)

        val date = Date(time * 1000)

        val dayOfTheWeek  = formater.format(date)

        return  dayOfTheWeek
    }

}