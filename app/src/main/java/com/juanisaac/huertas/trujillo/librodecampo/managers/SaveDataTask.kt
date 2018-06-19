package com.juanisaac.huertas.trujillo.librodecampo.managers

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import com.juanisaac.huertas.trujillo.database.AppDataBase
import com.juanisaac.huertas.trujillo.database.tables.Jornal
import com.juanisaac.huertas.trujillo.librodecampo.listener.CallBackSaveData

class SaveDataTask : AsyncTask<Void, Void, Boolean> {


    private val TAG = "SaveDataTask"
    lateinit var listener: CallBackSaveData
    val appDatabase: AppDataBase?
    lateinit var jornal:Jornal

    constructor(context: Context, appDatabase: AppDataBase?) {
        this.appDatabase = AppDataBase.Companion.getInstance(context)
    }

    override fun doInBackground(vararg params: Void?): Boolean {
        var ok: Boolean = false
        try {

            saveJornals(jornal, appDatabase)
            ok = true
        } catch (e: Exception) {
            appDatabase?.close()
            AppDataBase.destroyInstance()
            e.printStackTrace()
            listener.error(e.localizedMessage)
            listener.error("S")
        }

        return ok
    }

    override fun onPostExecute(aVoid: Boolean?) {
        super.onPostExecute(aVoid)
        AppDataBase.destroyInstance()
        if (aVoid!!)
            listener.finish()
    }

    override fun onCancelled() {
        super.onCancelled()
        appDatabase?.close()
        AppDataBase.destroyInstance()
    }

    fun saveJornals(jornal: Jornal, appDatabase: AppDataBase?) {
        Thread {

            appDatabase?.jornalDao()?.insert(
                    Jornal(jornal.tipoJornal, jornal.nombreTrabajador, jornal.tipoActividad, jornal.parcela,
                            jornal.dia, jornal.valorJornal))

            Log.d(TAG, "Save: ${jornal}")
        }.start()

    }
}