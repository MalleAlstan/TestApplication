package com.example.testapplication.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testapplication.model.data.CurrencyInfo

@Database(entities = [CurrencyInfo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun currencyInfoDao(): CurrencyInfoDao
}