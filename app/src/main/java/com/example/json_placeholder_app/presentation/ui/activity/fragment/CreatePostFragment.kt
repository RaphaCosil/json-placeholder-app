package com.example.json_placeholder_app.presentation.ui.activity.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.json_placeholder_app.databinding.FragmentCreatePostBinding
import com.example.json_placeholder_app.presentation.viewmodel.CreateAlbumViewModel
import com.example.json_placeholder_app.presentation.viewmodel.CreatePostViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreatePostFragment : Fragment() {
    private lateinit var binding: FragmentCreatePostBinding
    private val createPostViewModel: CreatePostViewModel by viewModel()
    private val createAlbumViewModel: CreateAlbumViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreatePostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}