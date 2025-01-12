package com.example.json_placeholder_app.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.json_placeholder_app.domain.entity.PostEntity
import com.example.json_placeholder_app.domain.usecase.GetPostsByUserIdUseCase
import com.example.json_placeholder_app.presentation.viewmodel.action.PostsOfUserAction
import com.example.json_placeholder_app.presentation.viewmodel.state.PostsOfUserState
import kotlinx.coroutines.launch

class PostsOfUserViewModel(
    private val getPostsByUserIdUseCase: GetPostsByUserIdUseCase
) : ViewModel() {
    private val _postsOfUserState = MutableLiveData<PostsOfUserState>()
    val postsOfUserState: LiveData<PostsOfUserState> = _postsOfUserState
    init {
        _postsOfUserState.value = PostsOfUserState()
    }

    fun handleAction(action: PostsOfUserAction) {
        when (action) {
            is PostsOfUserAction.LoadPosts -> getPostsByUserId(action.userId)
            is PostsOfUserAction.PostsLoaded -> onPostsLoaded(action.data)
            is PostsOfUserAction.Error -> onError(action.error)
        }
    }

    private fun onPostsLoaded(data: List<PostEntity>) {
        _postsOfUserState.value = _postsOfUserState.value?.copy(data = data)
    }

    private fun onError(error: String) {
        _postsOfUserState.value = _postsOfUserState.value?.copy(errorMessage = error)
    }

    fun getPostsByUserId(userId: Int) {
        viewModelScope.launch {
            try {
                val result = getPostsByUserIdUseCase.invoke(userId)
                onPostsLoaded(result)
            } catch (e: Exception) {
                Log.e("PostsViewModel",
                    "Error fetching todos | MESSAGE ${e.message} | CAUSE ${e.cause}"
                )
                onError(e.message.toString())
            }
        }
    }
}