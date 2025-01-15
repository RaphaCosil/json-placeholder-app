package com.example.json_placeholder_app.presentation.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.json_placeholder_app.R
import com.example.json_placeholder_app.databinding.ItemCommentBinding
import com.example.json_placeholder_app.domain.entity.CommentEntity

class CommentListAdapter(
    private val commentList: List<CommentEntity>
): RecyclerView.Adapter<CommentListAdapter.CommentViewHolder>() {

    inner class CommentViewHolder(val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root)
    override fun getItemCount() = commentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(ItemCommentBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.binding.apply {
            val comment = commentList[position]
            imageViewUser.setImageResource(R.drawable.photo_user)
            textViewUsername.text = comment.name
            textViewBody.text = comment.body
        }
    }
}