package com.example.json_placeholder_app.domain.usecase

import com.example.json_placeholder_app.domain.entity.CommentEntity
import com.example.json_placeholder_app.domain.repository.AppRepository

class GetPostCommentsUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(id: Int): List<CommentEntity> {
        return repository.getPostComments(id)
    }
}
