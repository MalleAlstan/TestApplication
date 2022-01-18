package com.example.testapplication.source.local

import android.database.SQLException
import com.example.testapplication.BaseTest
import com.example.testapplication.model.data.CurrencyInfo
import com.example.testapplication.repo.currency.CurrencyRepositoryImpl
import com.example.testapplication.source.Source
import com.example.testapplication.source.local.room.CurrencyInfoDao
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
class LocalSourceImplTest: BaseTest() {

    @InjectMocks
    private val currencyInfoDao = Mockito.mock(CurrencyInfoDao::class.java)
    private lateinit var localDataSource: LocalSourceImpl

    @Before
    fun setUp() {
        localDataSource = LocalSourceImpl(currencyInfoDao)
    }

    @Test
    fun fetchCurrencyNormalTest() {

        runBlockingTest {

            Mockito.`when`(currencyInfoDao.getAll()).thenReturn(mockCurrencyInfoList)

            val result = localDataSource.fetchCurrency().toList().first()
            Assert.assertEquals(mockCurrencyInfoList, result)
        }
    }

    private val mockCurrencyInfoList = listOf(
        CurrencyInfo("aaa", "AAA", "111"),
        CurrencyInfo("bbb", "BBB", "222"),
        CurrencyInfo("ccc", "CCC", "333"),
        CurrencyInfo("ddd", "DDD", "444"),
        CurrencyInfo("eee", "EEE", "555"),
        CurrencyInfo("fff", "FFF", "666"),
    )
}