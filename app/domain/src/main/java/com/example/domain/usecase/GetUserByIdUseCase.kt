package com.example.domain.usecase

import com.example.domain.entity.UserEntity
import com.example.domain.repository.AppRepository

class GetUserByIdUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(id: Int): UserEntity {
        return repository.getUserById(id)
    }
}