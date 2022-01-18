package com.example.testapplication.ui.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseActivityViewModel: ViewModel() {

    protected val TAG = this.javaClass.name

    protected var _toastMessage = MutableLiveData("")
    val toastMessage = _toastMessage
}