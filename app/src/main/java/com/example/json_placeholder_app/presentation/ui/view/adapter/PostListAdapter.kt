package com.example.json_placeholder_app.presentation.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.json_placeholder_app.R
import com.example.json_placeholder_app.databinding.ItemPostBinding
import com.example.json_placeholder_app.domain.entity.PostEntity
import com.example.json_placeholder_app.presentation.ui.view.click_listener.OnUserInformationClickListener

class PostListAdapter(
    private val postsList: List<PostEntity>,
    private val onUserInformationClickListener: OnUserInformationClickListener
) : RecyclerView.Adapter<PostListAdapter.PostViewHolder>() {
    inner class PostViewHolder(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root)
    override fun getItemCount() = postsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(ItemPostBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.binding.apply {
            val posts = postsList[position]
            textViewUsername.text = posts.userName
            textViewTitle.text = posts.title.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase() else it.toString()
            }
            textViewBody.text = posts.body.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase() else it.toString()
            }
            imageViewUser.setImageResource(R.drawable.photo_user)
        }
        holder.binding.imageViewUser.setOnClickListener {
            onUserInformationClickListener.onUserInformationClick(
                postsList[position].userId
            )
        }
        holder.binding.textViewUsername.setOnClickListener {
            onUserInformationClickListener.onUserInformationClick(
                postsList[position].userId
            )
        }
    }
}
