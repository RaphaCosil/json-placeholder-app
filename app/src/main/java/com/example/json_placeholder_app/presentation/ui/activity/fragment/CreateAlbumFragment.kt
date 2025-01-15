package com.example.json_placeholder_app.presentation.ui.activity.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.json_placeholder_app.databinding.FragmentCreateAlbumBinding
import com.example.json_placeholder_app.domain.entity.AlbumEntity
import com.example.json_placeholder_app.domain.entity.PhotoEntity
import com.example.json_placeholder_app.presentation.ui.view.adapter.PhotoListAdapter
import com.example.json_placeholder_app.presentation.ui.view.click_listener.OnPhotoClickListener
import com.example.json_placeholder_app.presentation.ui.view.style.SpaceItemDecoration
import com.example.json_placeholder_app.presentation.viewmodel.CreateAlbumViewModel
import com.example.json_placeholder_app.presentation.viewmodel.action.CreateAlbumAction
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateAlbumFragment : Fragment(), OnPhotoClickListener {
    private lateinit var binding: FragmentCreateAlbumBinding
    private val createAlbumViewModel: CreateAlbumViewModel by viewModel()
    private val photosSelected = emptyList<PhotoEntity>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateAlbumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createAlbumViewModel.handleAction(
            CreateAlbumAction.LoadPhotos
        )

        binding.btnCreateAlbum.setOnClickListener {
            val album = createAlbumEntity()
            if (album != null) {
                createAlbumViewModel.handleAction(
                    CreateAlbumAction.CreateAlbum(album)
                )
            }
        }
    }

    fun setupObserver() {
        createAlbumViewModel.createAlbumState.observe(viewLifecycleOwner) {
            loading(it.isLoading)
            it.errorMessage?.let { error ->
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun setupRecycler(photoList : List<PhotoEntity>) = binding.recyclerView .apply {
        val photoListAdapter = PhotoListAdapter(
            photoList,
            this@CreateAlbumFragment
        )
        layoutManager = LinearLayoutManager(requireContext())
        adapter = photoListAdapter
        addItemDecoration(SpaceItemDecoration(8))
    }

    private fun createAlbumEntity(): AlbumEntity? {
        val title = binding.editTextText.text
        return if (title.isNullOrEmpty()) {
            Toast.makeText(requireContext(), "Title cannot be empty", Toast.LENGTH_SHORT).show()
            null
        } else if (photosSelected.isEmpty()) {
            Toast.makeText(requireContext(), "Photos cannot be empty", Toast.LENGTH_SHORT).show()
            null
        } else {
            AlbumEntity(
                userId = 1,
                id = 1,
                title = title.toString(),
                photos = photosSelected,
                userName = "userName"
            )
        }
    }

    private fun loading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onPhotoClick(position: Int) {
        TODO("Not yet implemented")
    }
}