package com.example.testapplication.ui.activity.main


import com.example.testapplication.BaseTest
import com.example.testapplication.model.data.CurrencyInfo
import com.example.testapplication.repo.currency.CurrencyRepository
import com.example.testapplication.source.Response
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
    fun fetchCurrencySuccessTest() {

        runBlockingTest {
            Mockito.`when`(currencyRepository.fetchCurrencyList()).thenReturn(
                Response.Success(flowOf(mockCurrencyInfoList))
            )

            mainViewModel.fetchCurrency()

            Assert.assertEquals(mockCurrencyInfoList, mainViewModel.currencyList.value)
        }
    }

    @Test
    fun fetchCurrencyErrorTest() {

        runBlockingTest {
            Mockito.`when`(currencyRepository.fetchCurrencyList()).thenReturn(
                Response.Error(mockErrorMessage, flowOf())
            )

            mainViewModel.fetchCurrency()

            Assert.assertEquals(listOf<CurrencyInfo>(), mainViewModel.currencyList.value)
            Assert.assertEquals(mockErrorMessage, mainViewModel.toastMessage.value)
        }
    }

    @Test
    fun sortCurrencyNormalTest() {

        runBlockingTest {
            Mockito.`when`(currencyRepository.fetchCurrencyList()).thenReturn(
                Response.Success(flowOf(mockCurrencyInfoList))
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

            Assert.assertEquals(mockSelectedCurrencyInfo, mainViewModel.selectedCurrencyInfo.value)
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

    private val mockErrorMessage = "Mock Error Message"
}