package com.example.testapplication.ui.activity.main


import com.example.testapplication.BaseTest
import com.example.testapplication.model.data.CurrencyInfo
import com.example.testapplication.repo.currency.CurrencyRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class MainViewModelTest: BaseTest() {

    @InjectMocks
    private val currencyRepository = Mockito.mock(CurrencyRepository::class.java)
    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        mainViewModel = MainViewModel(currencyRepository)
    }

    @Test
    fun fetchCurrencyNormalTest() {

        runBlockingTest {
            Mockito.`when`(currencyRepository.fetchCurrencyList()).thenReturn(
                flowOf(mockCurrencyInfoList)
            )

            mainViewModel.fetchCurrency()

            Assert.assertEquals(mockCurrencyInfoList, mainViewModel.currencyList.value)
        }
    }

    @Test
    fun sortCurrencyNormalTest() {

        runBlockingTest {
            Mockito.`when`(currencyRepository.fetchCurrencyList()).thenReturn(
                flowOf(mockCurrencyInfoList)
            )

            mainViewModel.fetchCurrency()
            mainViewModel.sortCurrency()

            Assert.assertEquals(mockSortedCurrencyInfoList, mainViewModel.currencyList.value)
        }
    }

    @Test
    fun onSelectCurrencyInfoNormalTest() {

        runBlockingTest {

            mainViewModel.onSelectCurrencyInfo(mockSelectedCurrencyInfo)

            Assert.assertEquals(mockSelectedCurrencyInfo, mainViewModel.selectedCurrency.value)
        }
    }

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

    private val mockSelectedCurrencyInfo = CurrencyInfo("123", "SELECTED", "SLCT")
}