package com.example.json_placeholder_app.presentation.viewmodel.action

import com.example.json_placeholder_app.domain.entity.AlbumEntity

sealed class AlbumsOfUserAction {
    data class LoadData(val userId: Int) : AlbumsOfUserAction()
    data class DataLoaded(val data: List<AlbumEntity>) : AlbumsOfUserAction()
    data class Error(val error: String) : AlbumsOfUserAction()
}