package com.picpay.desafio.android.data

import com.picpay.desafio.android.data.database.ContactDatabase
import com.picpay.desafio.android.domain.ContactsState
import com.picpay.desafio.android.domain.interfaces.ContactsRepository
import com.picpay.desafio.android.domain.models.User
import com.picpay.desafio.android.domain.models.UserEntity
import com.picpay.desafio.android.extensions.toUser
import com.picpay.desafio.android.infrastructure.RetrofitInstance

class ContactsRepositoryImpl(private val db: ContactDatabase) : ContactsRepository {

    private suspend fun saveContacts(users: List<UserEntity>) {
        db.getContactDao().upsert(users)
    }

    override suspend fun getContacts(): List<User> {
        val savedContacts = db.getContactDao().getAllContacts()

        if (savedContacts.isNotEmpty()) {
            return savedContacts.map { it.toUser() }
        }

        val response = RetrofitInstance.api.getUsers()
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                saveContacts(resultResponse)
                return resultResponse.map { it.toUser() }
            }
        }

        return emptyList()
    }
}