package com.example.testapplication.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testapplication.model.data.CurrencyInfo

@Dao
interface CurrencyInfoDao {

    @Query("SELECT * FROM currencyinfo")
    fun getAll(): List<CurrencyInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(item: CurrencyInfo)
}