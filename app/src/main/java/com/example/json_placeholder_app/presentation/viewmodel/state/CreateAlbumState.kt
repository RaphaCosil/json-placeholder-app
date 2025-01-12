package com.example.json_placeholder_app.presentation.viewmodel.state

import com.example.json_placeholder_app.domain.entity.AlbumEntity

data class CreateAlbumState(
    val isLoading: Boolean = false,
    val album: AlbumEntity? = null,
    val isSuccessful: Boolean = false,
    val errorMessage: String? = null
)