package com.example

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.testapplication.CoroutineTestRule
import org.junit.Rule


abstract class BaseViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

}