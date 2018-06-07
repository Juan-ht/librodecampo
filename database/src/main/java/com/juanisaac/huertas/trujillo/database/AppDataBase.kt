package com.juanisaac.huertas.trujillo.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.juanisaac.huertas.trujillo.database.daos.JornalDao
import com.juanisaac.huertas.trujillo.database.tables.Jornal

@TypeConverters(Converters::class)
@Database(entities = arrayOf(Jornal::class), version = 1, exportSchema = false)
abstract class AppDataBase :RoomDatabase() {
    abstract fun jornalDao(): JornalDao
    companion object {
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase? {
            if (INSTANCE == null) {
                synchronized(AppDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            AppDataBase::class.java, "librodecampo.db")
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }


}
