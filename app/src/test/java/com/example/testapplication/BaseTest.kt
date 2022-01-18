package com.example.testapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import kotlin.coroutines.ContinuationInterceptor


abstract class BaseTest {

    // Dispatchers.XX
    @ExperimentalCoroutinesApi
    var testDispatcher = TestCoroutineDispatcher()

    // Dispatchers.Main
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // LiveData
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    inner class MainCoroutineRule : TestWatcher(), TestCoroutineScope by TestCoroutineScope() {

        override fun starting(description: Description) {
            super.starting(description)
            Dispatchers.setMain(this.coroutineContext[ContinuationInterceptor] as CoroutineDispatcher)
        }

        override fun finished(description: Description) {
            super.finished(description)
            Dispatchers.resetMain()
        }
    }
}
