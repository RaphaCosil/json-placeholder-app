package com.example.json_placeholder_app.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.json_placeholder_app.domain.entity.AlbumEntity
import com.example.json_placeholder_app.domain.usecase.GetAlbumsByUserIdUseCase
import kotlinx.coroutines.launch

class AlbumsOfUserViewModel(
    private val getAlbumsByUserIdUseCase: GetAlbumsByUserIdUseCase
) : ViewModel() {
    var albumList = MutableLiveData<List<AlbumEntity>>()
    fun getAlbumsByUserId(userId: Int) {
        viewModelScope.launch {
            try {
                val result = getAlbumsByUserIdUseCase.invoke(userId)
                albumList.value = result
            } catch (e: Exception) {
                Log.e("PostsViewModel",
                    "Error fetching todos | MESSAGE ${e.message} | CAUSE ${e.cause}"
                )
            }
        }
    }
}