package com.juanisaac.huertas.trujillo.librodecampo.model

import android.os.Parcel
import android.os.Parcelable

data class Jornal(var tipoJornal: String, var nombreTrabajador: String,
                  var tipoActividad: String, var parcela: String,
                  var dia: String, var valorJornal: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(tipoJornal)
        dest.writeString(nombreTrabajador)
        dest.writeString(tipoActividad)
        dest.writeString(parcela)
        dest.writeString(dia)
        dest.writeString(valorJornal)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Jornal> {
        override fun createFromParcel(parcel: Parcel): Jornal {
            return Jornal(parcel)
        }

        override fun newArray(size: Int): Array<Jornal?> {
            return arrayOfNulls(size)
        }
    }

}