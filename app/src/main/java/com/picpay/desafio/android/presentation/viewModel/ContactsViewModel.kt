package com.picpay.desafio.android.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.ContactsState
import com.picpay.desafio.android.domain.models.User
import com.picpay.desafio.android.domain.usecase.ContactsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ContactsViewModel(
    private val contactsUserCase: ContactsUseCase
) : ViewModel() {

    private val _users: MutableLiveData<ContactsState<List<User>>> = MutableLiveData()
    val users: LiveData<ContactsState<List<User>>> get() = _users

    init {
        getUsers()
    }

    fun getUsers() {
        _users.postValue(ContactsState.Loading())
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _users.postValue(contactsUserCase.getContacts())
            }
        }
    }

    fun reloadContacts() = getUsers()
}