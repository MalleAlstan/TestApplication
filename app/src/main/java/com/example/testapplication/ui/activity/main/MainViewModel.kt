package com.example.testapplication.ui.activity.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.testapplication.model.data.CurrencyInfo
import com.example.testapplication.repo.currency.CurrencyRepository
import com.example.testapplication.ui.activity.BaseActivityViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository
): BaseActivityViewModel() {

    private var _currencyList = MutableLiveData<List<CurrencyInfo>>(listOf())
    val currencyList: LiveData<List<CurrencyInfo>> = _currencyList

    private var _selectedCurrency = MutableLiveData(CurrencyInfo())
    val selectedCurrency: LiveData<CurrencyInfo> = _selectedCurrency

    private var currencyListJob: Job? = null

    fun fetchCurrency() {

        if (currencyListJob?.isActive == true) {
            currencyListJob?.cancel()
        }

        currencyListJob = viewModelScope.launch {
            yield()
            currencyRepository.fetchCurrencyList().collect {
                _currencyList.value = it
            }
        }
    }

    fun sortCurrency() {

        if (currencyListJob?.isActive == true) {
            return
        }

        currencyListJob = viewModelScope.launch {
            yield()
            _currencyList.value = getSortedCurrencyInfoList()
        }
    }

    private suspend fun getSortedCurrencyInfoList(): List<CurrencyInfo> =
        withContext(Dispatchers.IO) {
            currencyList.value?.sortedBy { it.name }?: listOf()
    }
}