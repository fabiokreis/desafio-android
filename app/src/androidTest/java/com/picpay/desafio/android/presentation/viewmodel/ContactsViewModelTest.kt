package com.picpay.desafio.android.presentation.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.picpay.desafio.android.data.ContactsRepository
import com.picpay.desafio.android.data.database.ContactDatabase
import com.picpay.desafio.android.domain.models.User
import com.picpay.desafio.android.presentation.viewModel.ContactsViewModel
import com.picpay.desafio.android.util.getLiveDataValue
import org.junit.*
import org.junit.Assert.assertFalse
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ContactViewModelTest{

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ContactsViewModel
    private lateinit var repository: ContactsRepository
    private lateinit var db: ContactDatabase

    @Before
    fun setUp() {

        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, ContactDatabase::class.java)
            .allowMainThreadQueries().build()
        repository = ContactsRepository(db)
        viewModel = ContactsViewModel(repository)
    }

    @Test
    fun should_save_and_return_saved_contacts(){
        val user = User("", "user", 1, "")
        viewModel.saveContacts(listOf(user))

        val user1 = viewModel.getSavedContacts().getLiveDataValue().find {user ->
            user.id == 1
            user.img == ""
            user.name == "user"
            user.username == ""
        }

        Assert.assertNotNull(user1)
    }

    @Test
    fun should_return_null_contacts(){
        val user1 = viewModel.getSavedContacts().getLiveDataValue().find {user ->
            user.id == 1
            user.img == ""
            user.name == "user"
            user.username == ""
        }

        Assert.assertNull(user1)
    }

    @Test
    fun should_return_users_contacts(){
        viewModel.getUsers()

        val users = viewModel.users.getLiveDataValue().data?.size
        assertFalse(users == 0)
    }

    @After
    fun closeDB(){
        db.clearAllTables()
        db.close()
    }
}