package com.picpay.desafio.android

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.data.PicPayService
import com.picpay.desafio.android.domain.models.User
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ExampleServiceTest {

    private val api = mock<PicPayService>()

    private val service = ExampleService(api)

    @Test
    fun exampleTest() = runBlocking {
        // given
        val expectedUsers = emptyList<User>()

        whenever(service.example()).thenReturn(expectedUsers)

        // when
        val users = service.example()

        // then
        assertEquals(users, expectedUsers)
    }
}