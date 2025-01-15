package com.example.json_placeholder_app.presentation.ui.activity.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.json_placeholder_app.R
import com.example.json_placeholder_app.databinding.FragmentCreatePublicationBinding

class CreatePublicationFragment : Fragment() {
    private lateinit var binding: FragmentCreatePublicationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreatePublicationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btCreatePost.setImageResource(R.drawable.icon_posts_fill)
        binding.btCreateAlbum.setImageResource(R.drawable.icon_albums_line)
        val fragment = CreatePostFragment()
        childFragmentManager.beginTransaction()
            .replace(binding.fragmentCreatePublication.id, fragment)
            .addToBackStack(null)
            .commit()

        binding.btCreatePost.setOnClickListener {
            binding.btCreatePost.setImageResource(R.drawable.icon_posts_fill)
            binding.btCreateAlbum.setImageResource(R.drawable.icon_albums_line)
            val fragmentCreatePost = CreatePostFragment()
            childFragmentManager.beginTransaction()
                .replace(binding.fragmentCreatePublication.id, fragmentCreatePost)
                .addToBackStack(null)
                .commit()
        }

        binding.btCreateAlbum.setOnClickListener {
            binding.btCreatePost.setImageResource(R.drawable.icon_posts_line)
            binding.btCreateAlbum.setImageResource(R.drawable.icon_albums_fill)
            val fragmentCreateAlbum = CreateAlbumFragment()
            childFragmentManager.beginTransaction()
                .replace(binding.fragmentCreatePublication.id, fragmentCreateAlbum)
                .addToBackStack(null)
                .commit()
        }
    }
}