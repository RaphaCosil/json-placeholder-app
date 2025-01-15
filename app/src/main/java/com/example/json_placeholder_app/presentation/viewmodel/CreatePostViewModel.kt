package com.example.json_placeholder_app.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.PostEntity
import com.example.domain.usecase.CreatePostUseCase
import com.example.json_placeholder_app.presentation.viewmodel.action.CreatePostAction
import com.example.json_placeholder_app.presentation.viewmodel.state.CreatePostState
import kotlinx.coroutines.launch

class CreatePostViewModel(
    private val createPostUseCase: CreatePostUseCase
): ViewModel() {
    private val _createPostState = MutableLiveData<CreatePostState>()
    val createPostState: LiveData<CreatePostState> = _createPostState
    init {
        _createPostState.value = CreatePostState()
    }

    fun handleAction(action: CreatePostAction) {
        when (action) {
            is CreatePostAction.CreatePost -> createPost(action.post)
            is CreatePostAction.PostCreated -> onPostCreated()
            is CreatePostAction.Error -> onError(action.error)
        }
    }

    private fun onPostCreated() {
        _createPostState.value = _createPostState.value?.copy(isSuccessful = true)
    }

    private fun onError(error: String) {
        _createPostState.value = _createPostState.value?.copy(errorMessage = error)
    }

    private fun createPost(post: PostEntity) {
        viewModelScope.launch {
            try {
                val result = createPostUseCase.invoke(post)
                _createPostState.value = _createPostState.value?.copy(isSuccessful = true)
                Log.d("PostsViewModel", "createPost result: $result")
            } catch (e: Exception) {
                Log.e("PostsViewModel", "Error fetching todos | MESSAGE ${e.message} | CAUSE ${e.cause}")
                onError(e.message.toString())
            }
        }
    }
}