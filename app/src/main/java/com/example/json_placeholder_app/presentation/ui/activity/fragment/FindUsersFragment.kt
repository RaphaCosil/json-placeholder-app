package com.example.json_placeholder_app.presentation.ui.activity.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.json_placeholder_app.R
import com.example.json_placeholder_app.presentation.viewmodel.FindUsersViewModel

class FindUsersFragment : Fragment() {

    companion object {
        fun newInstance() = FindUsersFragment()
    }

    private lateinit var viewModel: FindUsersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_find_users, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FindUsersViewModel::class.java)
        // TODO: Use the ViewModel
    }

}