package com.example.json_placeholder_app.presentation.ui.activity.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.json_placeholder_app.databinding.FragmentPostsOfUserBinding
import com.example.json_placeholder_app.domain.entity.FeedItemEntity
import com.example.json_placeholder_app.domain.entity.PostEntity
import com.example.json_placeholder_app.presentation.ui.view.adapter.PostListAdapter
import com.example.json_placeholder_app.presentation.ui.view.click_listener.OnUserInformationClickListener
import com.example.json_placeholder_app.presentation.ui.view.style.SpaceItemDecoration
import com.example.json_placeholder_app.presentation.viewmodel.PostsOfUserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

class PostsOfUserFragment : Fragment(), OnUserInformationClickListener {
    private lateinit var binding: FragmentPostsOfUserBinding
    private val viewModel: PostsOfUserViewModel by viewModel()
    private var userId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        arguments?.let {
            userId = it.getInt("userId")
        }
        binding = FragmentPostsOfUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            loading(true)
            viewModel.getPostsByUserId(userId)
            viewModel.postList.observe(viewLifecycleOwner) {
                setupRecycler(it)
                loading(false)
            }
        } catch (e: Exception) {
            Log.d("PostsOfUserFragment", "onViewCreated: $e")
            Toast.makeText(requireContext(), "Error fetching posts", Toast.LENGTH_SHORT).show()
            loading(false)
        }
    }
    private fun setupRecycler(postList: List<PostEntity>) = binding.postsRecycleView .apply {
        val postListAdapter = PostListAdapter(
            postList,
            this@PostsOfUserFragment
        )
        adapter = postListAdapter
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

    override fun onUserInformationClick(userId: Int) {
        Log.d("PostsOfUserFragment", "onUserInformationClick: $userId")
    }
}