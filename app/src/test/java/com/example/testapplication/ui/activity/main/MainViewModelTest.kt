package com.example.testapplication.ui.activity.main


import com.example.BaseViewModelTest
import com.example.testapplication.model.data.CurrencyInfo
import com.example.testapplication.repo.currency.CurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest: BaseViewModelTest() {

    @InjectMocks
    private val currencyRepository = Mockito.mock(CurrencyRepository::class.java)

    private lateinit var mainViewModel: MainViewModel

    private val mockCurrencyInfoList = listOf(
        CurrencyInfo("aaa", "AAA", "111"),
        CurrencyInfo("ccc", "CCC", "333"),
        CurrencyInfo("ddd", "DDD", "444"),
        CurrencyInfo("fff", "FFF", "666"),
        CurrencyInfo("bbb", "BBB", "222"),
        CurrencyInfo("eee", "EEE", "555"),
    )

    private val mockSortedCurrencyInfoList = listOf(
        CurrencyInfo("aaa", "AAA", "111"),
        CurrencyInfo("bbb", "BBB", "222"),
        CurrencyInfo("ccc", "CCC", "333"),
        CurrencyInfo("ddd", "DDD", "444"),
        CurrencyInfo("eee", "EEE", "555"),
        CurrencyInfo("fff", "FFF", "666"),
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(coroutineDispatcher)
        mainViewModel = MainViewModel(currencyRepository)
    }

    @Test
    fun fetchCurrencyNormal() {

        runBlockingTest {
            Mockito.`when`(currencyRepository.fetchCurrencyList()).thenReturn(
                flowOf(mockCurrencyInfoList)
            )

            mainViewModel.fetchCurrency()

            Assert.assertEquals(mainViewModel.currencyList.value, mockCurrencyInfoList)
        }
    }

    @Test
    fun sortCurrencyNormal() {

        runBlockingTest {
            Mockito.`when`(currencyRepository.fetchCurrencyList()).thenReturn(
                flowOf(mockCurrencyInfoList)
            )

            mainViewModel.fetchCurrency()
            mainViewModel.sortCurrency()

            Assert.assertEquals(mockSortedCurrencyInfoList, mainViewModel.currencyList.value)
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        coroutineDispatcher.cleanupTestCoroutines()
    }
}