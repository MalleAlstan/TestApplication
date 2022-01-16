package com.example.testapplication.source.local.room

import android.database.sqlite.SQLiteException
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testapplication.model.data.CurrencyInfo

@Dao
interface CurrencyInfoDao {

    @Query("SELECT * FROM currencyinfo")
    @Throws(SQLiteException::class)
    fun getAll(): List<CurrencyInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Throws(SQLiteException::class)
    fun insertAll(item: CurrencyInfo)
}