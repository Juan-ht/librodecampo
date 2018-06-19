package com.juanisaac.huertas.trujillo.librodecampo.listener

import com.juanisaac.huertas.trujillo.database.tables.Jornal

interface CallBackData {
    fun finishAction(jornal: ArrayList<Jornal>)

    fun error(s: String)
}