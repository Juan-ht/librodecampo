package com.juanisaac.huertas.trujillo.database

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.juanisaac.huertas.trujillo.database.tables.Jornal
import java.util.*
import kotlin.collections.ArrayList

class Converters {

    @TypeConverter
    fun fromStringToDate(value: String): Date {
        val listType = object : TypeToken<Date>() {
        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromDateToString(list: Date): String {
        val listType = object : TypeToken<Date>() {
        }.type
        val gson = Gson()
        return gson.toJson(list,listType)
    }



}