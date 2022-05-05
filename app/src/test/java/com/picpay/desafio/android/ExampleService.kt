package com.picpay.desafio.android

import com.picpay.desafio.android.data.PicPayService
import com.picpay.desafio.android.domain.models.UserEntity

class ExampleService(
    private val service: PicPayService
) {

    suspend fun example(): List<UserEntity> {
        service.getUsers()

        return emptyList()
    }
}