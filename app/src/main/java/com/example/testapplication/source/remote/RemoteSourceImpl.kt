package com.example.testapplication.source.remote

import com.example.testapplication.model.data.CurrencyInfo
import com.example.testapplication.source.Source
import com.example.testapplication.source.local.room.CurrencyInfoDao
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.lang.reflect.Type

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteSourceImpl @Inject constructor(
    private val currencyInfo: CurrencyInfoDao
) : Source {

    override suspend fun fetchCurrency(): Flow<List<CurrencyInfo>> {

        val currencyInfoList = getMockResponse()
        for (info in currencyInfoList) {
            currencyInfo.insertAll(info)
        }

        // save mock data to db only.
        return flowOf()
    }

    private fun getMockResponse(): List<CurrencyInfo> {

        val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val type: Type = Types.newParameterizedType(List::class.java, CurrencyInfo::class.java)
        val adapter: JsonAdapter<List<CurrencyInfo>> = moshi.adapter(type)
        return adapter.fromJson(mockJson)?: listOf()
    }

    private val mockJson = "[ \n" +
            "{ \n" +
            "\"id\": \"BTC\", \n" +
            "\"name\": \"Bitcoin\", \n" +
            "\"symbol\": \"BTC\" \n" +
            "}, \n" +
            "{ \n" +
            "\"id\": \"ETH\", \n" +
            "\"name\": \"Ethereum\", \n" +
            "\"symbol\": \"ETH\" \n" +
            "}, \n" +
            "{ \n" +
            "\"id\": \"XRP\", \n" +
            "\"name\": \"XRP\", \n" +
            "\"symbol\": \"XRP\" \n" +
            "}, \n" +
            "{ \n" +
            "\"id\": \"BCH\", \n" +
            "\"name\": \"Bitcoin Cash\", \n" +
            "\"symbol\": \"BCH\" \n" +
            "}, \n" +
            "{ \n" +
            "\"id\": \"LTC\", \n" +
            "\"name\": \"Litecoin\", \n" +
            "\"symbol\": \"LTC\" \n" +
            "}, \n" +
            "{ \n" +
            "\"id\": \"EOS\", \n" +
            "\"name\": \"EOS\", \n" +
            "\"symbol\": \"EOS\" \n" +
            "}, \n" +
            "{ \n" +
            "\"id\": \"BNB\", \n" +
            "\"name\": \"Binance Coin\", \n" +
            "\"symbol\": \"BNB\" \n" +
            "}, \n" +
            "{ \n" +
            "\"id\": \"LINK\", \n" +
            "\"name\": \"Chainlink\", \n" +
            "\"symbol\": \"LINK\" \n" +
            "}, \n" +
            "{\n" +
            "\n" +
            "\n" +
            "\n" +
            "\"id\": \"NEO\", \n" +
            "\"name\": \"NEO\", \n" +
            "\"symbol\": \"NEO\" \n" +
            "}, \n" +
            "{ \n" +
            "\"id\": \"ETC\", \n" +
            "\"name\": \"Ethereum Classic\", \n" +
            "\"symbol\": \"ETC\" \n" +
            "}, \n" +
            "{ \n" +
            "\"id\": \"ONT\", \n" +
            "\"name\": \"Ontology\", \n" +
            "\"symbol\": \"ONT\" \n" +
            "}, \n" +
            "{ \n" +
            "\"id\": \"CRO\", \n" +
            "\"name\": \"Crypto.com Chain\", \n" +
            "\"symbol\": \"CRO\" \n" +
            "}, \n" +
            "{ \n" +
            "\"id\": \"CUC\", \n" +
            "\"name\": \"Cucumber\", \n" +
            "\"symbol\": \"CUC\" \n" +
            "}, \n" +
            "{ \n" +
            "\"id\": \"USDC\", \n" +
            "\"name\": \"USD Coin\", \n" +
            "\"symbol\": \"USDC\" \n" +
            "} \n" +
            "]\n" +
            "\n"
}