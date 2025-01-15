package com.example.domain.usecase

import com.example.domain.entity.AlbumEntity
import com.example.domain.repository.AppRepository

class CreateAlbumUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(album: AlbumEntity): AlbumEntity? {
        return repository.createAlbum(album)
    }
}
