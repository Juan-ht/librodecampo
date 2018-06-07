package com.juanisaac.huertas.trujillo.librodecampo.model

import android.os.Parcel
import android.os.Parcelable
import java.text.SimpleDateFormat
import java.util.*

data class JornalModel(var tipoJornal: String, var nombreTrabajador: String,
                       var tipoActividad: String, var parcela: String,
                       var dia: Date = Date(), var valorJornal: String) {




}