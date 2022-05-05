package com.picpay.desafio.android.domain

sealed class ContactsState<T>(
    val data: T? = null,
    val message: String? = null) {

    class Success<T>(data: T?): ContactsState<T>(data)
    class Error<T>(message: String, data: T? = null): ContactsState<T>(data, message)
    class Loading<T>: ContactsState<T>()
}