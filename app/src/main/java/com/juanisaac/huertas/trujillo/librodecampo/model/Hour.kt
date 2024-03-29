package com.juanisaac.huertas.trujillo.librodecampo.model

import android.os.Parcel
import android.os.Parcelable
import java.text.SimpleDateFormat
import java.util.*

data class Hour(val time:Long, val temp:Double, val precip:Double, val timeZone:String): Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readDouble(),
            parcel.readDouble(),
            parcel.readString())




    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(time)
        parcel.writeDouble(temp)
        parcel.writeDouble(precip)
        parcel  .writeString(timeZone)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Hour> {
        override fun createFromParcel(parcel: Parcel): Hour {
            return Hour(parcel)
        }

        override fun newArray(size: Int): Array<Hour?> {
            return arrayOfNulls(size)
        }
    }

    fun getFormatDay():String{

        val formater = SimpleDateFormat("HH:mm", Locale.US)

        formater.timeZone = TimeZone.getTimeZone(timeZone)

        val date = Date(time * 1000)

        val dayOfTheWeek  = formater.format(date)

        return  dayOfTheWeek
    }
}