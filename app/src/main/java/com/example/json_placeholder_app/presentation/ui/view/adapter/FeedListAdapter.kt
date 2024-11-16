package com.example.json_placeholder_app.presentation.ui.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.json_placeholder_app.R
import com.example.json_placeholder_app.databinding.ItemAlbumBinding
import com.example.json_placeholder_app.databinding.ItemPostBinding
import com.example.json_placeholder_app.domain.entity.AlbumEntity
import com.example.json_placeholder_app.domain.entity.FeedItemEntity
import com.example.json_placeholder_app.domain.entity.PostEntity
import com.example.json_placeholder_app.presentation.ui.view.click_listener.OnCommentClickListener
import com.example.json_placeholder_app.presentation.ui.view.click_listener.OnUserInformationClickListener
import com.example.json_placeholder_app.presentation.ui.view.style.SpaceItemDecoration

class FeedListAdapter(
    private val context: Context,
    private val itemList: List<FeedItemEntity>,
    private val onUserInformationClickListener: OnUserInformationClickListener,
    private val onCommentClickListener: OnCommentClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val viewTypePost= 1
    private val viewTypeAlbum = 2

    override fun getItemViewType(position: Int): Int {
        return when (itemList[position]) {
            is FeedItemEntity.PostItem -> viewTypePost
            is FeedItemEntity.AlbumItem -> viewTypeAlbum
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            viewTypePost -> {
                val binding = ItemPostBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                PostViewHolder(binding)
            }
            viewTypeAlbum -> {
                val binding = ItemAlbumBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                AlbumViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PostViewHolder -> holder.bind(
                (itemList[position] as FeedItemEntity.PostItem).postEntity
            )
            is AlbumViewHolder -> holder.bind(
                (itemList[position] as FeedItemEntity.AlbumItem).albumEntity
            )
        }
    }

    override fun getItemCount(): Int = itemList.size

    inner class PostViewHolder(
        private val binding: ItemPostBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(postEntity: PostEntity) {
            binding.apply {
                textViewUsername.text = postEntity.userName
                textViewTitle.text = postEntity.title.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase() else it.toString()
                }
                textViewBody.text = postEntity.body.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase() else it.toString()
                }
                imageViewUser.setImageResource(R.drawable.photo_user)
                imageViewUser.setOnClickListener {
                    onUserInformationClickListener.onUserInformationClick(postEntity.userId)
                }
                textViewUsername.setOnClickListener {
                    onUserInformationClickListener.onUserInformationClick(postEntity.userId)
                }
                imageButtonComment.setOnClickListener {
                    onCommentClickListener.onCommentClick(postEntity.id)
                }
            }
        }
    }

    inner class AlbumViewHolder(
        private val binding: ItemAlbumBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(albumEntity: AlbumEntity) {
            binding.apply {
                textViewUsername.text = albumEntity.userName
                textViewTitle.text = albumEntity.title.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase() else it.toString()
                }
                imageViewUser.setImageResource(R.drawable.photo_user)
                val adapter = ImageListAdapter(albumEntity.photos)
                recyclerViewPhotos.layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                recyclerViewPhotos.adapter = adapter
                recyclerViewPhotos.addItemDecoration(SpaceItemDecoration(8))
                imageViewUser.setOnClickListener {
                    onUserInformationClickListener.onUserInformationClick(albumEntity.userId)
                }
                textViewUsername.setOnClickListener {
                    onUserInformationClickListener.onUserInformationClick(albumEntity.userId)
                }
            }
        }
    }
}
