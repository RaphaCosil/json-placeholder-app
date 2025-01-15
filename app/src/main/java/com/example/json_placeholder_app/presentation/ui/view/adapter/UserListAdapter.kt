package com.example.json_placeholder_app.presentation.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.json_placeholder_app.R
import com.example.json_placeholder_app.databinding.ItemUserBinding
import com.example.domain.entity.UserEntity
import com.example.json_placeholder_app.presentation.ui.view.click_listener.OnUserItemClickListener

class UserListAdapter(
    private val userList: List<UserEntity>,
    private val onUserItemClickListener: OnUserItemClickListener
): RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    inner class UserViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)
    override fun getItemCount() = userList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(ItemUserBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding.apply {
            val user = userList[position]
            textViewUsername.text = user.username
            imageViewUser.setImageResource(R.drawable.photo_user)
        }
        holder.itemView.setOnClickListener {
            onUserItemClickListener.onUserItemClick(userList[position].id)
        }
    }
}