package com.example.json_placeholder_app.presentation.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.json_placeholder_app.R
import com.example.json_placeholder_app.databinding.ItemPhotoBinding
import com.example.json_placeholder_app.domain.entity.PhotoEntity
import com.example.json_placeholder_app.presentation.ui.view.click_listener.OnPhotoClickListener

class PhotoListAdapter(
    private val photoUrlList: List<PhotoEntity>,
    private val onPhotoClickListener: OnPhotoClickListener
) : RecyclerView.Adapter<PhotoListAdapter.ImageViewHolder>() {
    class ImageViewHolder(
        val binding: ItemPhotoBinding
    ) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemPhotoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ImageViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return photoUrlList.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageUrl = photoUrlList[position].url
        Glide.with(holder.binding.image.context)
            .load(imageUrl)
            .placeholder(R.drawable.photo_placeholder)
            .error(R.drawable.photo_placeholder)
            .into(holder.binding.image)

        holder.itemView.setOnClickListener {
            onPhotoClickListener.onPhotoClick(position)
        }
    }
}
