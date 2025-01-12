package com.example.json_placeholder_app.presentation.viewmodel.state

import com.example.json_placeholder_app.domain.entity.CommentEntity

data class CommentsOfUserState (
    val isLoading: Boolean = false,
    var data: List<CommentEntity> = emptyList(),
    val errorMessage: String? = null
)