package com.example.json_placeholder_app.presentation.viewmodel.state

import com.example.json_placeholder_app.domain.entity.PostEntity

data class PostsOfUserState (
    val isLoading: Boolean = false,
    var data: List<PostEntity> = emptyList(),
    val errorMessage: String? = null
)