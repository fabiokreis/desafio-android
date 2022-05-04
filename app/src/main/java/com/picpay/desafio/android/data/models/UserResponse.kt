package com.picpay.desafio.android.data.models

import com.google.gson.annotations.SerializedName
import com.picpay.desafio.android.domain.models.User
import java.io.Serializable

data class UserResponse(
    @SerializedName("img") val img: String,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int,
    @SerializedName("username") val username: String
) : Serializable

fun UserResponse.toEntity() = User(
    img = img,
    name = name,
    id = id,
    username = username
)