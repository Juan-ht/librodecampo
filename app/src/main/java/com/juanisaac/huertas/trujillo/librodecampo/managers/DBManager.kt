package com.juanisaac.huertas.trujillo.librodecampo.managers

import android.content.Context
import android.util.Log
import com.juanisaac.huertas.trujillo.database.AppDataBase
import com.juanisaac.huertas.trujillo.database.tables.Jornal
import com.juanisaac.huertas.trujillo.librodecampo.listener.CallBackData
import com.juanisaac.huertas.trujillo.librodecampo.listener.CallBackSaveData
import java.util.ArrayList

class DBManager {

    private val TAG = "DBMANAGER"
    lateinit var appDatabase: AppDataBase

    @Synchronized
    fun provideCallManager(): DBManager {
        return DBManager()
    }

    fun saveData(context: Context, listener: CallBackSaveData) {
        val task = SaveDataTask(context, appDatabase)
        task.execute()
    }

    private fun open(context: Context) {
        appDatabase = AppDataBase.getInstance(context)!!
        if (appDatabase == null || !appDatabase.isOpen())
            appDatabase = AppDataBase.getInstance(context)!!

    }

    fun close() {
        try {
            appDatabase.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun getListJornal(listener: CallBackData,context: Context) {
        appDatabase = AppDataBase.getInstance(context)!!
        open(context)
        Thread {
            try {
                val jornalList = appDatabase.jornalDao().getAll() as ArrayList<Jornal>
                listener.finishAction(jornalList)
            } catch (e: NullPointerException) {
                e.printStackTrace()
                close()
                Log.d("DBManager", e.printStackTrace().toString())
            }
        }.start()
        close()
    }

    fun deleteAll(listener: CallBackData,context: Context) {
        appDatabase = AppDataBase.getInstance(context)!!
        open(context)
        Thread {
            try {
                val jornalList = appDatabase.jornalDao().deleteAll()
                close()
            } catch (e: NullPointerException) {
                e.printStackTrace()
                close()
                Log.d("DBManager", e.printStackTrace().toString())
            }
        }.start()
    }

}