package com.example.json_placeholder_app.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.json_placeholder_app.domain.entity.AlbumEntity
import com.example.json_placeholder_app.domain.entity.PhotoEntity
import com.example.json_placeholder_app.domain.usecase.CreateAlbumUseCase
import com.example.json_placeholder_app.domain.usecase.CreatePhotoUseCase
import com.example.json_placeholder_app.domain.usecase.GetPhotosUseCase
import kotlinx.coroutines.launch

class CreateAlbumViewModel(
    private val getPhotoUseCase: GetPhotosUseCase,
    private val createAlbumUseCase: CreateAlbumUseCase,
    private val createPhotoUseCase: CreatePhotoUseCase
): ViewModel() {
    val isSuccessful = MutableLiveData<Boolean>()
    val photoList = MutableLiveData<List<PhotoEntity>>()
    fun createAlbum(album: AlbumEntity) {
        try {
            viewModelScope.launch {
                val result = createAlbumUseCase.invoke(album)
                for (photo in album.photos) {
                    photo.albumId = result?.id!!
                    createPhotoUseCase.invoke(photo)
                }
                isSuccessful.value = true
            }
        } catch (e: Exception) {
            isSuccessful.value = false
            Log.e(
                "PostsViewModel",
                "Error fetching todos | MESSAGE ${e.message} | CAUSE ${e.cause}"
            )
        }
    }

    fun getPhotos(){
        try {
            viewModelScope.launch {
                val result = getPhotoUseCase.invoke()
                photoList.value = result
            }
        } catch (e: Exception) {
            Log.e(
                "PostsViewModel",
                "Error fetching todos | MESSAGE ${e.message} | CAUSE ${e.cause}"
            )
        }
    }

}