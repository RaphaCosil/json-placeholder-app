package com.example.json_placeholder_app.presentation.viewmodel.action

import com.example.json_placeholder_app.domain.entity.AlbumEntity

sealed class CreateAlbumAction {
    data class CreateAlbum(val album: AlbumEntity): CreateAlbumAction()
    data class AlbumCreated(val album: AlbumEntity): CreateAlbumAction()
    data class Error(val error: String): CreateAlbumAction()
}