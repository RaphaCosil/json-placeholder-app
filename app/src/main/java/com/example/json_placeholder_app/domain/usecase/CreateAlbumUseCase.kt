package com.example.json_placeholder_app.domain.usecase

import com.example.json_placeholder_app.domain.entity.AlbumEntity
import com.example.json_placeholder_app.domain.repository.AppRepository

class CreateAlbumUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(album: AlbumEntity): AlbumEntity? {
        return repository.createAlbum(album)
    }
}
