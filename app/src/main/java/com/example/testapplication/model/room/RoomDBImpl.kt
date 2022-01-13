package com.example.testapplication.model.room

import android.util.Log
import androidx.room.Room
import com.example.testapplication.TestApplication
import com.example.testapplication.model.data.CurrencyInfo
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomDBImpl @Inject constructor(): RoomDB {

    val roomDb = Room.databaseBuilder(
        TestApplication().appContext(),
        AppDatabase::class.java, "test-database"
    ).build()

    override suspend fun queryCurrencyInfoList(): Flow<List<CurrencyInfo>> {
        return flowOf(roomDb.currencyInfoDao().getAll())
    }

    override suspend fun insertCurrencyInfoList(list: List<CurrencyInfo>) {
        for (info in list) {
            roomDb.currencyInfoDao().insertAll(info)
        }
    }
}