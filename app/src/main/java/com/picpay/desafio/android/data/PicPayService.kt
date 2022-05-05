package com.picpay.desafio.android.data

import com.picpay.desafio.android.domain.models.UserEntity
import retrofit2.Response
import retrofit2.http.GET

interface PicPayService {

    @GET("users")
    suspend fun getUsers(): Response<List<UserEntity>>
}