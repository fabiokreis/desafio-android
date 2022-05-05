package com.picpay.desafio.android.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.domain.ContactsState
import com.picpay.desafio.android.presentation.ContactsActivity
import com.picpay.desafio.android.presentation.adapter.UserListAdapter
import com.picpay.desafio.android.presentation.viewModel.ContactsViewModel
import kotlinx.android.synthetic.main.fragment_contacts.*

class ContactsFragment : Fragment(R.layout.fragment_contacts) {
    lateinit var viewModel: ContactsViewModel

    lateinit var userAdapter: UserListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as ContactsActivity).viewModel

        setupRecyclerView()
        setupObservers()
        setupRefreshListener()
    }

    private fun setupObservers() {
        with(viewModel) {
            users.observe(viewLifecycleOwner, Observer { response ->
                when (response) {
                    is ContactsState.Success -> {
                        hideProgressBar()
                        response.data?.let { usersResponse ->
                            userAdapter.setData(usersResponse)
                            saveContacts(usersResponse)
                        }
                    }
                    is ContactsState.Error -> {
                        hideProgressBar()
                    }
                    is ContactsState.Loading -> {
                        showProgressBar()
                    }
                }
            })

            getSavedContacts().observe(viewLifecycleOwner, Observer { savedContacts ->
                if (savedContacts == null || savedContacts.isEmpty()) getUsers()
                else userAdapter.setData(savedContacts)
            })
        }
    }

    private fun setupRecyclerView() {
        userAdapter = UserListAdapter()
        recyclerView.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun setupRefreshListener() {
        swipeRefreshLayout.setOnRefreshListener {
            recyclerView.visibility = View.INVISIBLE
            viewModel.getUsers()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun hideProgressBar() {
        user_list_progress_bar.visibility = View.INVISIBLE
        recyclerView.visibility = View.VISIBLE
    }

    private fun showProgressBar() {
        user_list_progress_bar.visibility = View.VISIBLE
    }
}