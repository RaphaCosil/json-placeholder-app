package com.example.json_placeholder_app.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.AlbumEntity
import com.example.domain.entity.PhotoEntity
import com.example.domain.usecase.CreateAlbumUseCase
import com.example.domain.usecase.GetPhotosUseCase
import com.example.json_placeholder_app.presentation.viewmodel.action.CreateAlbumAction
import com.example.json_placeholder_app.presentation.viewmodel.state.CreateAlbumState
import kotlinx.coroutines.launch

class CreateAlbumViewModel(
    private val getPhotoUseCase: GetPhotosUseCase,
    private val createAlbumUseCase: CreateAlbumUseCase
): ViewModel() {
    private val _createAlbumState = MutableLiveData<CreateAlbumState>()
    val createAlbumState: LiveData<CreateAlbumState> = _createAlbumState

    init{
        _createAlbumState.value = CreateAlbumState()
    }

    fun handleAction(action: CreateAlbumAction) {
        when (action) {
            is CreateAlbumAction.LoadPhotos -> getPhotos()
            is CreateAlbumAction.PhotosLoaded -> onPhotosLoaded(action.photos)
            is CreateAlbumAction.CreateAlbum -> createAlbum(action.album)
            is CreateAlbumAction.AlbumCreated -> onAlbumCreated(action.album)
            is CreateAlbumAction.Error -> onError(action.error)
        }
    }

    private fun getPhotos() {
        viewModelScope.launch {
            try {
                val result = getPhotoUseCase.invoke()
                _createAlbumState.value = _createAlbumState.value?.copy(photos = result)
            } catch (e: Exception) {
                Log.e("CreateAlbumViewModel",
                    "Error fetching photos | MESSAGE ${e.message} | CAUSE ${e.cause}"
                )
                onError(
                    e.message ?: "Error fetching photos"
                )
            }
        }
    }

    private fun onPhotosLoaded(photos: List<PhotoEntity>) {
        _createAlbumState.value = _createAlbumState.value?.copy(isSuccessful = true)
        _createAlbumState.value = _createAlbumState.value?.copy(photos = photos)
    }

    private fun onAlbumCreated(album: AlbumEntity) {
        _createAlbumState.value = _createAlbumState.value?.copy(isSuccessful = true)
        _createAlbumState.value = _createAlbumState.value?.copy(album = album)
    }

    private fun onError(error: String) {
        _createAlbumState.value = _createAlbumState.value?.copy(errorMessage = error)
    }

    private fun createAlbum(album: AlbumEntity) {
        viewModelScope.launch {
            try {
                createAlbumUseCase.invoke(album)
                _createAlbumState.value = _createAlbumState.value?.copy(isSuccessful = true)
            } catch (e: Exception) {
                Log.e("CreateAlbumViewModel",
                    "Error creating album | MESSAGE ${e.message} | CAUSE ${e.cause}"
                )
                onError(
                    e.message ?: "Error creating album"
                )
            }
        }
    }
}