package com.picpay.desafio.android.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.data.ContactsRepository
import com.picpay.desafio.android.domain.ContactsState
import com.picpay.desafio.android.domain.models.User
import kotlinx.coroutines.launch
import retrofit2.Response

class ContactsViewModel(private val contactRepository: ContactsRepository) : ViewModel() {

    val users: MutableLiveData<ContactsState<List<User>>> = MutableLiveData()

    init {
        getSavedContacts()
    }

    fun getUsers() = viewModelScope.launch {
        users.postValue(ContactsState.Loading())
        val response = contactRepository.getUsers()
        users.postValue(handleUsersResponse(response))
    }

    private fun handleUsersResponse(response: Response<List<User>>): ContactsState<List<User>> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return ContactsState.Success(resultResponse)
            }
        }
        return ContactsState.Error(response.message())
    }

    fun saveContacts(user: List<User>) = viewModelScope.launch {
        contactRepository.upsert(user)
    }

    fun getSavedContacts() = contactRepository.getSavedContacts()
}