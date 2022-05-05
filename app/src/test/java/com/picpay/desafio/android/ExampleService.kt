package com.picpay.desafio.android

import com.picpay.desafio.android.data.PicPayService
import com.picpay.desafio.android.domain.models.User

class ExampleService(
    private val service: PicPayService
) {

    suspend fun example(): List<User> {
        service.getUsers()

        return emptyList()
    }
}