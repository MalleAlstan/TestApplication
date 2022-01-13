package com.example.testapplication.source.local

import com.example.testapplication.model.data.CurrencyInfo
import com.example.testapplication.source.Source
import com.example.testapplication.source.local.room.RoomDB
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalSourceImpl @Inject constructor(
    private val roomDb: RoomDB
): Source {

    override suspend fun fetchCurrency(): Flow<List<CurrencyInfo>> {

        return roomDb.queryCurrencyInfoList()
    }
}