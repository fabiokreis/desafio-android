package com.picpay.desafio.android.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

abstract class UnitTest {

    @ExperimentalCoroutinesApi
    private val testDispatcher =  TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setupTest() {
        initialize()
        with(Dispatchers) { setMain(testDispatcher) }
    }

    abstract fun initialize()

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}