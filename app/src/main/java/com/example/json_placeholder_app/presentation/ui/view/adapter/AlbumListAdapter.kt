package com.example.json_placeholder_app.presentation.ui.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.json_placeholder_app.R
import com.example.json_placeholder_app.databinding.ItemAlbumBinding
import com.example.domain.entity.AlbumEntity
import com.example.json_placeholder_app.presentation.ui.view.click_listener.OnUserInformationClickListener
import com.example.json_placeholder_app.presentation.ui.view.style.SpaceItemDecoration

class AlbumListAdapter(
    private val context: Context,
    private val albumList: List<AlbumEntity>,
    private val onUserInformationClickListener: OnUserInformationClickListener
) : RecyclerView.Adapter<AlbumListAdapter.AlbumViewHolder>() {

    inner class AlbumViewHolder(val binding: ItemAlbumBinding) : RecyclerView.ViewHolder(binding.root)
    override fun getItemCount() = albumList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder(ItemAlbumBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.binding.apply {
            val album = albumList[position]
            textViewUsername.text = album.userName
            textViewTitle.text = album.title.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase() else it.toString()
            }
            imageViewUser.setImageResource(R.drawable.photo_user)
            val adapter = ImageListAdapter(album.photos)
            recyclerViewPhotos.adapter = adapter
            recyclerViewPhotos.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            recyclerViewPhotos.addItemDecoration(SpaceItemDecoration(8))
            recyclerViewPhotos.setHasFixedSize(true)
        }
        holder.binding.imageViewUser.setOnClickListener {
            onUserInformationClickListener.onUserInformationClick(
                albumList[position].userId
            )
        }
        holder.itemView.setOnClickListener {
            onUserInformationClickListener.onUserInformationClick(
                albumList[position].userId
            )
        }
    }
}