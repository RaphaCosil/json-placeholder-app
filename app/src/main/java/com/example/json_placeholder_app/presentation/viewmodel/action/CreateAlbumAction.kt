package com.example.json_placeholder_app.presentation.viewmodel.action

import com.example.json_placeholder_app.domain.entity.AlbumEntity
import com.example.json_placeholder_app.domain.entity.PhotoEntity

sealed class CreateAlbumAction {
    data object LoadPhotos : CreateAlbumAction()
    data class PhotosLoaded(val photos: List<PhotoEntity>) : CreateAlbumAction()
    data class CreateAlbum(val album: AlbumEntity): CreateAlbumAction()
    data class AlbumCreated(val album: AlbumEntity): CreateAlbumAction()
    data class Error(val error: String): CreateAlbumAction()
}