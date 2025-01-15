package com.example.domain.usecase

import com.example.domain.entity.PhotoEntity
import com.example.domain.repository.AppRepository

class GetPhotosUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(): List<PhotoEntity> {
        return repository.getPhotos()
    }
}
