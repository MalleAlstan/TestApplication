package com.example.testapplication.model.room

import com.example.testapplication.model.data.CurrencyInfo
import kotlinx.coroutines.flow.Flow


interface RoomDB {

    suspend fun queryCurrencyInfoList(): Flow<List<CurrencyInfo>>
    suspend fun insertCurrencyInfoList(list: List<CurrencyInfo>)
}