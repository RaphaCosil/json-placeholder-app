package com.example.json_placeholder_app.domain.usecase

import com.example.json_placeholder_app.domain.entity.PhotoEntity
import com.example.json_placeholder_app.domain.repository.AppRepository

class GetPhotosUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(): List<PhotoEntity> {
        return repository.getPhotos()
    }
}
