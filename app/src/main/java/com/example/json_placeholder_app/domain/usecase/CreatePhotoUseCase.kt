package com.example.json_placeholder_app.domain.usecase

import com.example.json_placeholder_app.domain.entity.PhotoEntity
import com.example.json_placeholder_app.domain.repository.AppRepository

class CreatePhotoUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(photo: PhotoEntity) {
        repository.createPhoto(photo)
    }
}