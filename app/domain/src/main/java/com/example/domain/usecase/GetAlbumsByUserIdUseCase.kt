package com.example.domain.usecase

import com.example.domain.entity.AlbumEntity
import com.example.domain.repository.AppRepository

class GetAlbumsByUserIdUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(id: Int): List<AlbumEntity> {
        return repository.getAlbumsByUserId(id)
    }
}
