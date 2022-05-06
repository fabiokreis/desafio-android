package com.picpay.desafio.android.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.picpay.desafio.android.domain.ContactsState
import com.picpay.desafio.android.domain.models.User
import com.picpay.desafio.android.domain.usecase.ContactsUseCase
import com.picpay.desafio.android.presentation.viewModel.ContactsViewModel
import com.picpay.desafio.android.util.UnitTest
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class ContactsViewModelTest : UnitTest() {
    private val contactsUserCase = mockk<ContactsUseCase>(relaxed = true)

    private val usersObserver: Observer<ContactsState<List<User>>> = mockk(relaxed = true)

    private lateinit var viewModel: ContactsViewModel

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    override fun initialize() {
        viewModel = ContactsViewModel(contactsUserCase)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun should_getUsers_is_successful() {
        runBlockingTest {
            val user = User("img", "name", 1, "username")

            val usersMock: ContactsState<List<User>> = ContactsState.Success(listOf(user))

            val contactStateSlot = mutableListOf<ContactsState<List<User>>>()

            with(viewModel) {
                users.observeForever(usersObserver)
            }

            coEvery {
                contactsUserCase.getContacts()
            } returns usersMock

            viewModel.getUsers()

            coVerify {
                contactsUserCase.getContacts()
            }

            verify {
                usersObserver.onChanged(capture(contactStateSlot))
            }

            contactStateSlot shouldNotBe null
            contactStateSlot.size shouldBe 4

            confirmVerified(
                contactsUserCase
            )
        }
    }
}