package com.example.domain.usecase

import com.example.domain.entity.PostEntity
import com.example.domain.repository.AppRepository

class GetPostsByUserIdUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(id: Int): List<PostEntity> {
        return repository.getPostsByUserId(id)
    }
}
