package com.example.testapplication.source.local

import com.example.testapplication.model.data.CurrencyInfo
import com.example.testapplication.source.Source
import com.example.testapplication.source.local.room.CurrencyInfoDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalSourceImpl @Inject constructor(
    private val currencyInfo: CurrencyInfoDao
): Source {

    override suspend fun fetchCurrency(): Flow<List<CurrencyInfo>> {
        return flowOf(currencyInfo.getAll())
    }
}