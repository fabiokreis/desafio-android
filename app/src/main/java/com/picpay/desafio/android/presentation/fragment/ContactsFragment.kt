package com.picpay.desafio.android.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.picpay.desafio.android.R
import com.picpay.desafio.android.presentation.ContactsActivity
import com.picpay.desafio.android.presentation.viewModel.ContactsViewModel

class ContactsFragment : Fragment(R.layout.fragment_contacts) {
    lateinit var viewModel: ContactsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as ContactsActivity).viewModel
    }

}