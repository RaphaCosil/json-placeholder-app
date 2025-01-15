package com.example.json_placeholder_app.presentation.viewmodel.action

import com.example.domain.entity.PostEntity

sealed class CreatePostAction {
    data class CreatePost(val post: PostEntity): CreatePostAction()
    data class PostCreated(val post: PostEntity): CreatePostAction()
    data class Error(val error: String): CreatePostAction()
}