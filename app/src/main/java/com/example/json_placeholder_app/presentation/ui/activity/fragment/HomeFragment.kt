package com.example.json_placeholder_app.presentation.ui.activity.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.json_placeholder_app.R
import com.example.json_placeholder_app.databinding.FragmentHomeBinding
import com.example.json_placeholder_app.domain.entity.FeedItemEntity
import com.example.json_placeholder_app.presentation.ui.activity.CommentsOfUserActivity
import com.example.json_placeholder_app.presentation.ui.view.adapter.FeedListAdapter
import com.example.json_placeholder_app.presentation.ui.view.click_listener.OnCommentClickListener
import com.example.json_placeholder_app.presentation.ui.view.click_listener.OnUserInformationClickListener
import com.example.json_placeholder_app.presentation.ui.view.style.SpaceItemDecoration
import com.example.json_placeholder_app.presentation.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), OnUserInformationClickListener, OnCommentClickListener {
    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            loading(true)
            homeViewModel.getFeedList()
            homeViewModel.feedItemList.observe(viewLifecycleOwner) {
                Log.d("PostsViewModel", "getPosts result: $it")
                setupRecycler(it)
                loading(false)
            }
        } catch (e: Exception) {
            Log.e("PostsViewModel", "Error fetching todos | MESSAGE ${e.message} | CAUSE ${e.cause}")
            Toast.makeText(requireContext(), "Error fetching posts", Toast.LENGTH_SHORT).show()
            loading(false)
        }
    }
    private fun setupRecycler(feedList: List<FeedItemEntity>) = binding.feedRecycleView .apply {
        val feedListAdapter = FeedListAdapter(
            requireActivity(),
            feedList,
            this@HomeFragment,
            this@HomeFragment
        )
        adapter = feedListAdapter
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

    override fun onCommentClick(postId: Int) {
        val intent = Intent(requireContext(), CommentsOfUserActivity::class.java)
        intent.putExtra("postId", postId)
        startActivity(intent)
    }
}