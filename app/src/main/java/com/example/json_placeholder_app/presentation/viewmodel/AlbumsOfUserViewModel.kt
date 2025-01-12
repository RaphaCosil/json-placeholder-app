package com.example.json_placeholder_app.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.json_placeholder_app.domain.entity.AlbumEntity
import com.example.json_placeholder_app.domain.usecase.GetAlbumsByUserIdUseCase
import com.example.json_placeholder_app.presentation.viewmodel.action.AlbumsOfUserAction
import com.example.json_placeholder_app.presentation.viewmodel.state.AlbumsOfUserState
import kotlinx.coroutines.launch

class AlbumsOfUserViewModel(
    private val getAlbumsByUserIdUseCase: GetAlbumsByUserIdUseCase
) : ViewModel() {
    private val _getAlbumsOfUserState = MutableLiveData<AlbumsOfUserState>()
    val getAlbumsOfUserState: LiveData<AlbumsOfUserState> = _getAlbumsOfUserState
    init {
        _getAlbumsOfUserState.value = AlbumsOfUserState()
    }

    fun handleAction(action: AlbumsOfUserAction) {
        when (action) {
            is AlbumsOfUserAction.LoadData -> loadData()
            is AlbumsOfUserAction.DataLoaded -> onDataLoaded(action.data)
            is AlbumsOfUserAction.Error -> onError(action.error)
        }
    }

    private fun loadData() {
        _getAlbumsOfUserState.value = _getAlbumsOfUserState.value?.copy(isLoading = true)
        getAlbumsByUserId(1)
    }

    private fun onDataLoaded(data: List<AlbumEntity>) {
        _getAlbumsOfUserState.value = _getAlbumsOfUserState.value?.copy(isLoading = false, data = data)
    }

    private fun onError(error: String) {
        _getAlbumsOfUserState.value = _getAlbumsOfUserState.value?.copy(isLoading = false, errorMessage = error)
    }

    fun getAlbumsByUserId(userId: Int) {
        viewModelScope.launch {
            try {
                val result = getAlbumsByUserIdUseCase.invoke(userId)
                onDataLoaded(result)
            } catch (e: Exception) {
                Log.e("PostsViewModel",
                    "Error fetching todos | MESSAGE ${e.message} | CAUSE ${e.cause}"
                )
                onError(e.message.toString())
            }
        }
    }
}