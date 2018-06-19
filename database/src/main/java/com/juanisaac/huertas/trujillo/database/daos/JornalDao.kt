package com.juanisaac.huertas.trujillo.database.daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.juanisaac.huertas.trujillo.database.tables.Jornal
import java.util.*

@Dao
interface JornalDao {

    @Query("SELECT * FROM jornal")
    fun getAll(): List<Jornal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(jornal: ArrayList<Jornal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(jornal: Jornal)

    @Query("DELETE FROM jornal")
    fun deleteAll()


}