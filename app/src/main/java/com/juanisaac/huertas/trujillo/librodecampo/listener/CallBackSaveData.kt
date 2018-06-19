package com.juanisaac.huertas.trujillo.librodecampo.listener

interface CallBackSaveData {
    abstract fun finish()

    abstract fun error(s: String)
}