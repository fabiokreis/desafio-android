package com.picpay.desafio.android.infrastructure

import com.picpay.desafio.android.data.ContactsRepositoryImpl
import com.picpay.desafio.android.data.database.ContactDatabase
import com.picpay.desafio.android.domain.interfaces.ContactsRepository
import com.picpay.desafio.android.presentation.adapter.UserListAdapter
import com.picpay.desafio.android.presentation.viewModel.ContactsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module as koinModule

object ContactsDI {
    val module = koinModule {
        viewModel { ContactsViewModel(get()) }

        factory { UserListAdapter() }
        factory<ContactsRepository> {
            ContactsRepositoryImpl(db = ContactDatabase(context = androidContext()))
        }
    }

}