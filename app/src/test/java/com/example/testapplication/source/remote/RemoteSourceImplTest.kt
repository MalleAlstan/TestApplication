package com.example.testapplication.source.remote

import com.example.testapplication.BaseTest
import com.example.testapplication.model.data.CurrencyInfo
import com.example.testapplication.source.local.LocalSourceImpl
import com.example.testapplication.source.local.room.CurrencyInfoDao
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class RemoteSourceImplTest: BaseTest() {

    @InjectMocks
    private val currencyInfoDao = Mockito.mock(CurrencyInfoDao::class.java)
//    private val adapter = Mockito.spy(JsonAdapter::class.java) as JsonAdapter<List<CurrencyInfo>>
    private lateinit var localDataSource: RemoteSourceImpl

    @Before
    fun setUp() {

        val type = Types.newParameterizedType(List::class.java, CurrencyInfo::class.java)
        localDataSource = RemoteSourceImpl(
            currencyInfoDao, Moshi.Builder().add(KotlinJsonAdapterFactory()).build().adapter(type))
    }

    @Test
    fun fetchCurrencyNormalTest() {

        runBlockingTest {

            val result = localDataSource.fetchCurrency().data?.toList()
            Assert.assertEquals(listOf<CurrencyInfo>(), result)
        }
    }
}