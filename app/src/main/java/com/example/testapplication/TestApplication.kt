package com.example.testapplication

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TestApplication : Application() {

    companion object {
        private lateinit var instance: Context
    }

    override fun onCreate() {
        super.onCreate()

        instance = applicationContext
    }

    fun appContext(): Context {
        return instance
    }
}