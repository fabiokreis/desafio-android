package com.picpay.desafio.android.data

import com.picpay.desafio.android.data.database.ContactDatabase
import com.picpay.desafio.android.domain.models.User
import com.picpay.desafio.android.infrastructure.RetrofitInstance

class ContactsRepository(private val db: ContactDatabase) {

    suspend fun getUsers() = RetrofitInstance.api.getUsers()

    suspend fun upsert(users: List<User>) = db.getContactDao().upsert(users)

    fun getSavedContacts() = db.getContactDao().getAllContacts()
}