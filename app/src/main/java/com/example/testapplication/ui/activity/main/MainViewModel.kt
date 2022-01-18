package com.example.testapplication.ui.activity.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.testapplication.model.data.CurrencyInfo
import com.example.testapplication.repo.currency.CurrencyRepository
import com.example.testapplication.source.Response
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

    private var _selectedCurrencyInfo = MutableLiveData(CurrencyInfo())
    val selectedCurrencyInfo: LiveData<CurrencyInfo> = _selectedCurrencyInfo

    private var currencyListJob: Job? = null

    fun fetchCurrency() {

        if (currencyListJob?.isActive == true) {
            currencyListJob?.cancel()
        }

        currencyListJob = viewModelScope.launch {
            yield()
            val result = currencyRepository.fetchCurrencyList()

            if (result is Response.Success) {
                result.data?.collect {
                    _currencyList.value = it
                }
            } else {
                _toastMessage.value = result.message
            }
        }
    }

    fun sortCurrency() {

        if (currencyListJob?.isActive == true) {
            return
        }

        currencyListJob = viewModelScope.launch {
            yield()
            _currencyList.value = currencyList.value?.sortedBy { it.name }
        }
    }

    fun onSelectCurrencyInfo(currencyInfo: CurrencyInfo) {
        _selectedCurrencyInfo.value = currencyInfo
    }
}