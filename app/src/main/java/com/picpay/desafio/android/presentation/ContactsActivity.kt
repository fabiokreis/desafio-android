package com.picpay.desafio.android.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.ContactsRepository
import com.picpay.desafio.android.data.database.ContactDatabase
import com.picpay.desafio.android.infrastructure.factory.ContactsViewModelProviderFactory
import com.picpay.desafio.android.presentation.viewModel.ContactsViewModel

class ContactsActivity : AppCompatActivity(R.layout.activity_contacts) {

    lateinit var viewModel: ContactsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val contactsRepository = ContactsRepository(ContactDatabase(this))
        val viewModelProviderFactory = ContactsViewModelProviderFactory(contactsRepository)
        viewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(ContactsViewModel::class.java)
    }
}