package com.example.json_placeholder_app.presentation.ui.activity.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.json_placeholder_app.R
import com.example.json_placeholder_app.databinding.FragmentUserProfileBinding
import com.example.json_placeholder_app.presentation.viewmodel.UserProfileViewModel
import kotlin.properties.Delegates

class UserProfileFragment : Fragment() {
    private lateinit var binding: FragmentUserProfileBinding
    private val viewModel: UserProfileViewModel by viewModel()
    private var userId: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        arguments?.let {
            userId = it.getInt("userId")
        }
        binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUser(userId)
        viewModel.users.observe(viewLifecycleOwner) {
            binding.textViewUsername.text = it.username
            binding.btPostsOfUser.setImageResource(R.drawable.icon_posts_fill)
            binding.btAlbumsOfUser.setImageResource(R.drawable.icon_albums_line)
            val fragment = PostsOfUserFragment()
            val bundle = Bundle()
            bundle.putInt("userId", userId)
            fragment.arguments = bundle
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()

            binding.btPostsOfUser.setOnClickListener {
                binding.btPostsOfUser.setImageResource(R.drawable.icon_posts_fill)
                binding.btAlbumsOfUser.setImageResource(R.drawable.icon_albums_line)
                val fragment = PostsOfUserFragment()
                val bundle = Bundle()
                bundle.putInt("userId", userId)
                fragment.arguments = bundle
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit()
            }
            binding.btAlbumsOfUser.setOnClickListener {
                binding.btPostsOfUser.setImageResource(R.drawable.icon_posts_line)
                binding.btAlbumsOfUser.setImageResource(R.drawable.icon_albums_fill)
                val fragment = AlbumsOfUserFragment()
                val bundle = Bundle()
                bundle.putInt("userId", userId)
                fragment.arguments = bundle
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit()
            }
        }
    }
}
