package com.example.json_placeholder_app.presentation.viewmodel.state

import com.example.domain.entity.AlbumEntity
import com.example.domain.entity.PhotoEntity

data class CreateAlbumState(
    val isLoading: Boolean = false,
    val photos: List<PhotoEntity> = emptyList(),
    val album: AlbumEntity? = null,
    val isSuccessful: Boolean = false,
    val errorMessage: String? = null
)