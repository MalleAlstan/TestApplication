package com.example.testapplication.repo.currency

import com.example.testapplication.model.data.CurrencyInfo
import com.example.testapplication.source.Response
import kotlinx.coroutines.flow.Flow


interface CurrencyRepository {

    suspend fun fetchCurrencyList(): Response<Flow<List<CurrencyInfo>>>
}