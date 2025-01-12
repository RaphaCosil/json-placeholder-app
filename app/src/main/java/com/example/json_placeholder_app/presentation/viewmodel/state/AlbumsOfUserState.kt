package com.example.json_placeholder_app.presentation.viewmodel.state

import com.example.json_placeholder_app.domain.entity.AlbumEntity

data class AlbumsOfUserState (
    val isLoading: Boolean = false,
    var data: List<AlbumEntity> = emptyList(),
    val errorMessage: String? = null
)