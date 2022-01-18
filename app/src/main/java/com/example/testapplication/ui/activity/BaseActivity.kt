package com.example.testapplication.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {

    protected val TAG = this.javaClass.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        bindData()
    }

    abstract fun initView()
    abstract fun bindData()

    protected fun showToast(toastMessage: String) {
        if (toastMessage.isNotEmpty()) {
            Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
        }
    }
}