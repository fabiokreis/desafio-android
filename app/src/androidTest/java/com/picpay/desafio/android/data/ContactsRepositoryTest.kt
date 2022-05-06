package com.picpay.desafio.android.data

import com.picpay.desafio.android.domain.models.User
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ContactsRepositoryTest {

    private val contactsRepository = mockk<ContactsRepositoryImpl>()

    @Test
    fun should_return_saved_contacts() {
        runBlocking {
            val user = User("img", "name", 1, "username")

            val usersMock = listOf(user)

            coEvery {
                contactsRepository.getContacts()
            } answers { usersMock }

            val usersSaved = contactsRepository.getContacts()

            coVerify(exactly = 1) {
                contactsRepository.getContacts()
            }

            assert(usersSaved == usersMock)
        }
    }
}