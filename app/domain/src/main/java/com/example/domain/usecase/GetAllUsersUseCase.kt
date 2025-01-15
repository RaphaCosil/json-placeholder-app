package com.example.domain.usecase

import com.example.domain.entity.UserEntity
import com.example.domain.repository.AppRepository

class GetAllUsersUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(): List<UserEntity> {
        return repository.getAllUsers()
    }
}
