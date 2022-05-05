package com.picpay.desafio.android.domain.interfaces

import com.picpay.desafio.android.domain.ContactsState
import com.picpay.desafio.android.domain.models.User

interface ContactsRepository {
    suspend fun getSavedContacts(): ContactsState<List<User>>
}