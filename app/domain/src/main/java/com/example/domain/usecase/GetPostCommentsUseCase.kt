package com.example.domain.usecase

import com.example.domain.entity.CommentEntity
import com.example.domain.repository.AppRepository

class GetPostCommentsUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(id: Int): List<CommentEntity> {
        return repository.getPostComments(id)
    }
}
