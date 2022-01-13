package com.example.testapplication.repo.currency

import android.util.Log
import com.example.testapplication.di.SourceModule.RemoteData
import com.example.testapplication.di.SourceModule.LocalData
import com.example.testapplication.model.data.CurrencyInfo
import com.example.testapplication.source.Source
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CurrencyRepositoryImpl @Inject constructor(
    @RemoteData private val remoteSource: Source,
    @LocalData private val localSource: Source
) : CurrencyRepository {

    var hasLocalData = false

    override suspend fun fetchCurrencyList(): Flow<List<CurrencyInfo>> {

        val response: Flow<List<CurrencyInfo>>?
        if (hasLocalData) {
            response = localSource.fetchCurrency()
        } else {
            response = remoteSource.fetchCurrency()
            hasLocalData = true
        }

        return response
    }
}