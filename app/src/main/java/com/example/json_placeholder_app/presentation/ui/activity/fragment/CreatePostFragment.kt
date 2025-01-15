package com.example.json_placeholder_app.presentation.ui.activity.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.json_placeholder_app.databinding.FragmentCreatePostBinding
import com.example.domain.entity.PostEntity
import com.example.json_placeholder_app.presentation.viewmodel.CreatePostViewModel
import com.example.json_placeholder_app.presentation.viewmodel.action.CreatePostAction
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreatePostFragment : Fragment() {
    private lateinit var binding: FragmentCreatePostBinding
    private val createPostViewModel: CreatePostViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreatePostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCreatePost.setOnClickListener {
            val post = createPostEntity()
            if (post != null) {
                createPostViewModel.handleAction(
                    CreatePostAction.CreatePost(post)
                )
            }
        }

        setupObserver()
    }

    fun setupObserver() {
        createPostViewModel.createPostState.observe(viewLifecycleOwner) {
            loading(it.isLoading)
            it.errorMessage?.let { error ->
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
            }
            it.isSuccessful.let { success ->
                if (success) {
                    binding.editTextTitle.text = null
                    binding.editTextDescription.text = null
                }
            }
        }
    }

    private fun loading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }


    private fun createPostEntity(): PostEntity? {
        if (binding.editTextTitle.text.isNullOrBlank() || binding.editTextDescription.text.isNullOrBlank()) {
            return null
        }
        return PostEntity(
            userId = 1,
            id = 0,
            title = binding.editTextTitle.text.toString(),
            body = binding.editTextDescription.text.toString(),
            userName = "us"
        )
    }
}