package com.example.json_placeholder_app.presentation.viewmodel.action

import com.example.json_placeholder_app.domain.entity.CommentEntity

sealed class CommentsOfUserAction {
    data class LoadComments(val userId: Int): CommentsOfUserAction()
    data class CommentsLoaded(val data: List<CommentEntity>): CommentsOfUserAction()
    data class Error(val error: String): CommentsOfUserAction()
}