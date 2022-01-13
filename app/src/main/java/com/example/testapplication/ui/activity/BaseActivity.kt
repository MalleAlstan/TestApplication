package com.example.testapplication.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

abstract class BaseActivity : AppCompatActivity() {

    abstract fun initView()
    abstract fun bindData()
}