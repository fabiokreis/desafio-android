package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.domain.ContactsState
import com.picpay.desafio.android.domain.interfaces.ContactsRepository
import com.picpay.desafio.android.domain.models.User

class ContactsUseCase(private val repository: ContactsRepository) {

    suspend fun getContacts(): ContactsState<List<User>> = repository.getSavedContacts()

}