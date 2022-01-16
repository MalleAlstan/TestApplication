package com.example.testapplication.ui.activity.main

import com.example.BaseViewModelTest
import com.example.testapplication.model.data.CurrencyInfo
import com.example.testapplication.repo.currency.CurrencyRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito


class MainViewModelTest: BaseViewModelTest() {

    @InjectMocks
    private val currencyRepository = Mockito.mock(CurrencyRepository::class.java)

    private lateinit var mainViewModel: MainViewModel
    private val mockCurrencyInfoList = listOf(
        CurrencyInfo("aaa", "AAA", "111"),
        CurrencyInfo("bbb", "BBB", "222"),
        CurrencyInfo("ccc", "CCC", "333"),
        CurrencyInfo("ddd", "DDD", "444"),
        CurrencyInfo("eee", "EEE", "555"),
        CurrencyInfo("fff", "FFF", "666"),
    )

    @Before
    fun beforeClass() {
        mainViewModel = MainViewModel(currencyRepository)
    }

    @Test
    fun fetchCurrencyNormal() {

        runBlockingTest {
            Mockito.`when`(currencyRepository.fetchCurrencyList()).thenReturn(
                flowOf(mockCurrencyInfoList)
            )

            mainViewModel.fetchCurrency()

            val result = mainViewModel.currencyList.value
            assert(result == mockCurrencyInfoList)
        }
    }

    @Test
    fun fetchCurrencyHasJob() {

        runBlockingTest {
            Mockito.`when`(currencyRepository.fetchCurrencyList()).thenReturn(
                flowOf(mockCurrencyInfoList)
            )

            mainViewModel.fetchCurrency()

            val result = mainViewModel.currencyList.value
            assert(result == mockCurrencyInfoList)
        }
    }
}