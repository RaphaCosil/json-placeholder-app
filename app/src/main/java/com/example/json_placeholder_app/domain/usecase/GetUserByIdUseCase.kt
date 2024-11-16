package com.example.json_placeholder_app.domain.usecase

import com.example.json_placeholder_app.domain.entity.UserEntity
import com.example.json_placeholder_app.domain.repository.AppRepository

class GetUserByIdUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(id: Int): UserEntity {
        return repository.getUserById(id)
    }
}