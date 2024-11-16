package com.example.json_placeholder_app.domain.usecase

import com.example.json_placeholder_app.domain.entity.PostEntity
import com.example.json_placeholder_app.domain.repository.AppRepository

class GetPostsByUserIdUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(id: Int): List<PostEntity> {
        return repository.getPostsByUserId(id)
    }
}
