package com.example.domain.usecase

import com.example.domain.entity.PhotoEntity
import com.example.domain.repository.AppRepository

class CreatePhotoUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(photo: PhotoEntity) {
        repository.createPhoto(photo)
    }
}