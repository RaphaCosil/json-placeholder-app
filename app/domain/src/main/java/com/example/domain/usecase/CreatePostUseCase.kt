package com.example.domain.usecase

import com.example.domain.entity.PostEntity
import com.example.domain.repository.AppRepository

class CreatePostUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(post: PostEntity) {
        repository.createPost(post)
    }
}
