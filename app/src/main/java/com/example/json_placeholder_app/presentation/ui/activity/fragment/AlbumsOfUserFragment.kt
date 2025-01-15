package com.example.json_placeholder_app.presentation.ui.activity.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.json_placeholder_app.databinding.FragmentAlbumsOfUserBinding
import com.example.domain.entity.AlbumEntity
import com.example.json_placeholder_app.presentation.ui.view.adapter.AlbumListAdapter
import com.example.json_placeholder_app.presentation.ui.view.click_listener.OnUserInformationClickListener
import com.example.json_placeholder_app.presentation.ui.view.style.SpaceItemDecoration
import com.example.json_placeholder_app.presentation.viewmodel.AlbumsOfUserViewModel
import com.example.json_placeholder_app.presentation.viewmodel.action.AlbumsOfUserAction
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlbumsOfUserFragment : Fragment(), OnUserInformationClickListener {
    private lateinit var binding: FragmentAlbumsOfUserBinding
    private val viewModel: AlbumsOfUserViewModel by viewModel()
    private var userId: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        arguments?.let {
            userId = it.getInt("userId")
        }
        binding = FragmentAlbumsOfUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loading(true)
        try {
            viewModel.handleAction(AlbumsOfUserAction.LoadData(userId))
            setupObserver()
        } catch (e: Exception) {
            Log.d("AlbumsOfUserFragment", "onViewCreated: $e")
            Toast.makeText(requireContext(), "Error fetching albums", Toast.LENGTH_SHORT).show()
            loading(false)
        }
    }

    private fun setupObserver() {
        viewModel.getAlbumsOfUserState.observe(viewLifecycleOwner) {
            loading(it.isLoading)
            setupRecycler(it.data)
            it.errorMessage?.let { error ->
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRecycler(albumList: List<AlbumEntity>) = binding.albumsRecycleView .apply {
        val albumListAdapter = AlbumListAdapter(
            requireActivity(),
            albumList,
            this@AlbumsOfUserFragment
        )
        adapter = albumListAdapter
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
        Log.d("AlbumsOfUserFragment", "onUserInformationClick: $userId")
    }
}
