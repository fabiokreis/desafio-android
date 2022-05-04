package com.picpay.desafio.android.infrastructure.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.picpay.desafio.android.data.ContactsRepository
import com.picpay.desafio.android.presentation.viewModel.ContactsViewModel

class ContactsViewModelProviderFactory(private val contactsRepository: ContactsRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ContactsViewModel(contactsRepository) as T
    }
}