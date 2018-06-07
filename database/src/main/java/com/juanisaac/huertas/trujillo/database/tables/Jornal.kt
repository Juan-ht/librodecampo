package com.juanisaac.huertas.trujillo.database.tables

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "jornal")
data class Jornal(
        var tipoJornal: String,
        var nombreTrabajador: String,
        var tipoActividad: String,
        var parcela: String,
        @PrimaryKey(autoGenerate = false)var dia: Date,
        var valorJornal: String
)