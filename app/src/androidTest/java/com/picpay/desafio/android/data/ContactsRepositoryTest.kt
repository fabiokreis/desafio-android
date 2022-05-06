package com.picpay.desafio.android.data

import com.picpay.desafio.android.domain.ContactsState
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

            val usersMock: ContactsState<List<User>> = ContactsState.Success(listOf(user))

            coEvery {
                contactsRepository.getSavedContacts()
            } answers { usersMock }

            val usersSaved = contactsRepository.getSavedContacts()

            coVerify(exactly = 1) {
                contactsRepository.getSavedContacts()
            }

            assert(usersSaved == usersMock)
        }
    }
}