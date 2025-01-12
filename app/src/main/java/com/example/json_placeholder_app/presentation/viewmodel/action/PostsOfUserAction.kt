package com.example.json_placeholder_app.presentation.viewmodel.action

import com.example.json_placeholder_app.domain.entity.PostEntity

sealed class PostsOfUserAction {
    data class LoadPosts(val userId: Int): PostsOfUserAction()
    data class PostsLoaded(val data: List<PostEntity>): PostsOfUserAction()
    data class Error(val error: String): PostsOfUserAction()
}