package com.example.testapplication.repo.currency

import com.example.testapplication.model.data.CurrencyInfo
import kotlinx.coroutines.flow.Flow


interface CurrencyRepository {

    suspend fun fetchCurrencyList(): Flow<List<CurrencyInfo>>
}