package com.example.json_placeholder_app.presentation.ui.activity.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.json_placeholder_app.R
import com.example.json_placeholder_app.databinding.FragmentFindUsersBinding
import com.example.domain.entity.UserEntity
import com.example.json_placeholder_app.presentation.ui.view.adapter.UserListAdapter
import com.example.json_placeholder_app.presentation.ui.view.click_listener.OnUserItemClickListener
import com.example.json_placeholder_app.presentation.ui.view.style.SpaceItemDecoration
import com.example.json_placeholder_app.presentation.viewmodel.FindUsersViewModel
import com.example.json_placeholder_app.presentation.viewmodel.action.FindUsersAction
import org.koin.androidx.viewmodel.ext.android.viewModel

class FindUsersFragment : Fragment(), OnUserItemClickListener {
    private lateinit var binding: FragmentFindUsersBinding
    private val viewModel: FindUsersViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFindUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            viewModel.handleAction(FindUsersAction.LoadUsers)
            setupObserver()
        } catch (e: Exception) {
            Log.d("FindUsersFragment", "onViewCreated: $e")
            Toast.makeText(requireContext(), "Error fetching users", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupObserver() {
        viewModel.findUsersState.observe(viewLifecycleOwner) {
            loading(it.isLoading)
            setupRecycler(it.data)
            it.errorMessage?.let { error ->
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRecycler(userList: List<UserEntity>) = binding.usersRecycleView .apply {
        val userListAdapter = UserListAdapter(
            userList,
            this@FindUsersFragment
        )
        adapter = userListAdapter
        layoutManager = LinearLayoutManager(context)
        addItemDecoration(SpaceItemDecoration(48))
    }

    private fun loading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onUserItemClick(userId: Int) {
        val fragment = UserProfileFragment()
        val bundle = Bundle()
        bundle.putInt("userId", userId)
        fragment.arguments = bundle
        val parentFragmentManager = parentFragmentManager
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment, fragment)
            .addToBackStack(null)
            .commit()
    }
}