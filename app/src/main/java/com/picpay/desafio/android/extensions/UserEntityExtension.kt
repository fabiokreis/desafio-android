package com.picpay.desafio.android.extensions

import com.picpay.desafio.android.domain.models.User
import com.picpay.desafio.android.domain.models.UserEntity

fun UserEntity.toUser() = User(
    img = img,
    name = name,
    id = id,
    username = username
)