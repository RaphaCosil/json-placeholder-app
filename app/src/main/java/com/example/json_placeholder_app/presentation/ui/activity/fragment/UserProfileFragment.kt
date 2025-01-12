package com.example.json_placeholder_app.presentation.ui.activity.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.json_placeholder_app.R
import com.example.json_placeholder_app.databinding.FragmentUserProfileBinding
import com.example.json_placeholder_app.presentation.viewmodel.UserProfileViewModel

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

        binding.btPostsOfUser.setImageResource(R.drawable.icon_posts_fill)
        binding.btAlbumsOfUser.setImageResource(R.drawable.icon_albums_line)

        viewModel.getUser(userId)
        viewModel.users.observe(viewLifecycleOwner) {
            binding.textViewUsername.text = it.username
            val fragmentStandard = PostsOfUserFragment()
            val bundleStandard = Bundle()
            bundleStandard.putInt("userId", userId)
            fragmentStandard.arguments = bundleStandard
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragmentStandard)
                .commit()

            binding.btPostsOfUser.setOnClickListener {
                binding.btPostsOfUser.setImageResource(R.drawable.icon_posts_fill)
                binding.btAlbumsOfUser.setImageResource(R.drawable.icon_albums_line)
                val fragmentPost = PostsOfUserFragment()
                val bundlePost = Bundle()
                bundlePost.putInt("userId", userId)
                fragmentPost.arguments = bundlePost
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragmentPost)
                    .commit()
            }

            binding.btAlbumsOfUser.setOnClickListener {
                binding.btPostsOfUser.setImageResource(R.drawable.icon_posts_line)
                binding.btAlbumsOfUser.setImageResource(R.drawable.icon_albums_fill)
                val fragmentAlbums = AlbumsOfUserFragment()
                val bundleAlbums = Bundle()
                bundleAlbums.putInt("userId", userId)
                fragmentAlbums.arguments = bundleAlbums
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragmentAlbums)
                    .commit()
            }
        }
    }
}
