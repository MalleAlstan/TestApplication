package com.example.testapplication.repo.currency

import com.example.testapplication.BaseTest
import com.example.testapplication.model.data.CurrencyInfo
import com.example.testapplication.source.Response
import com.example.testapplication.source.Source
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class CurrencyRepositoryTest: BaseTest() {

    @InjectMocks
    private val localDataSource = Mockito.mock(Source::class.java)
    private val remoteDataSource = Mockito.mock(Source::class.java)
    private lateinit var currencyRepository: CurrencyRepositoryImpl

    @Before
    fun setUp() {
        currencyRepository = CurrencyRepositoryImpl(
            remoteDataSource, localDataSource, testDispatcher
        )
    }

    @Test
    fun fetchCurrencySuccessTest() {

        runBlockingTest {

            Mockito.`when`(remoteDataSource.fetchCurrency()).thenReturn(
                Response.Success(flowOf(mockRemoteCurrencyInfoList))
            )

            Mockito.`when`(localDataSource.fetchCurrency()).thenReturn(
                Response.Success(flowOf(mockLocalCurrencyInfoList))
            )

            // should from remote
            val remoteResult = currencyRepository.fetchCurrencyList().data?.toList()?.first()
            Assert.assertEquals(mockRemoteCurrencyInfoList, remoteResult)

            // should from local
            val localResult= currencyRepository.fetchCurrencyList().data?.toList()?.first()
            Assert.assertEquals(mockLocalCurrencyInfoList, localResult)
        }
    }

    @Test
    fun fetchCurrencyRemoteErrorTest() {

        runBlockingTest {

            Mockito.`when`(remoteDataSource.fetchCurrency()).thenReturn(
                Response.Error(mockErrorMessage, flowOf())
            )

            // should from remote
            val errorResult = currencyRepository.fetchCurrencyList() as Response.Error<Flow<List<CurrencyInfo>>>

            Assert.assertEquals(listOf<CurrencyInfo>(), errorResult.data?.toList())
            Assert.assertEquals(mockErrorMessage, errorResult.message)
        }
    }

    @Test
    fun fetchCurrencyLocalErrorTest() {

        runBlockingTest {

            Mockito.`when`(remoteDataSource.fetchCurrency()).thenReturn(
                Response.Success(flowOf(mockRemoteCurrencyInfoList))
            )

            Mockito.`when`(localDataSource.fetchCurrency()).thenReturn(
                Response.Error(mockErrorMessage, flowOf())
            )

            // for triggering local flag
            currencyRepository.fetchCurrencyList()

            val errorResult = currencyRepository.fetchCurrencyList() as Response.Error<Flow<List<CurrencyInfo>>>

            Assert.assertEquals(listOf<CurrencyInfo>(), errorResult.data?.toList())
            Assert.assertEquals(mockErrorMessage, errorResult.message)
        }
    }

    private val mockRemoteCurrencyInfoList = listOf(
        CurrencyInfo("aaa", "AAA", "111"),
        CurrencyInfo("bbb", "BBB", "222"),
        CurrencyInfo("ccc", "CCC", "333"),
        CurrencyInfo("ddd", "DDD", "444"),
        CurrencyInfo("eee", "EEE", "555"),
        CurrencyInfo("fff", "FFF", "666"),
    )

    private val mockLocalCurrencyInfoList = listOf(
        CurrencyInfo("Laaa", "LAAA", "L111"),
        CurrencyInfo("Lbbb", "LBBB", "L222"),
        CurrencyInfo("Lccc", "LCCC", "L333"),
        CurrencyInfo("Lddd", "LDDD", "L444"),
        CurrencyInfo("Leee", "LEEE", "L555"),
        CurrencyInfo("Lfff", "LFFF", "L666"),
    )

    private val mockErrorMessage = "Mock Error Message"
}
