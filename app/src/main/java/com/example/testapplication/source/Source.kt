package com.example.testapplication.source

import com.example.testapplication.model.data.CurrencyInfo
import kotlinx.coroutines.flow.Flow


interface Source {

    suspend fun fetchCurrency(): Flow<List<CurrencyInfo>>
}