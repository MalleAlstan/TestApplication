package com.example.testapplication.repo.currency

import com.example.testapplication.di.DispatcherModule
import com.example.testapplication.di.SourceModule.LocalData
import com.example.testapplication.di.SourceModule.RemoteData
import com.example.testapplication.model.data.CurrencyInfo
import com.example.testapplication.source.Response
import com.example.testapplication.source.Source
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


class CurrencyRepositoryImpl @Inject constructor(
    @RemoteData private val remoteSource: Source,
    @LocalData private val localSource: Source,
    @DispatcherModule.IoDispatcher private val ioDispatcher: CoroutineDispatcher
): CurrencyRepository {

    private var hasLocalData = false

    override suspend fun fetchCurrencyList(): Response<Flow<List<CurrencyInfo>>> =
        withContext(ioDispatcher){
            val response: Response<Flow<List<CurrencyInfo>>>

            if (hasLocalData) {
                response = localSource.fetchCurrency()
            } else {
                response = remoteSource.fetchCurrency()
                hasLocalData = true
            }
            response
    }
}