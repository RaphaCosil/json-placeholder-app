package com.example.json_placeholder_app.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.CommentEntity
import com.example.domain.usecase.GetPostCommentsUseCase
import com.example.json_placeholder_app.presentation.viewmodel.action.CommentsOfUserAction
import com.example.json_placeholder_app.presentation.viewmodel.state.CommentsOfUserState
import kotlinx.coroutines.launch

class CommentsOfUserViewModel(
    private val getPostCommentsUseCase: GetPostCommentsUseCase
): ViewModel() {
    private val _commentsOfUserState = MutableLiveData<CommentsOfUserState>()
    val commentsOfUserState: LiveData<CommentsOfUserState> = _commentsOfUserState
    init {
        _commentsOfUserState.value = CommentsOfUserState()
    }

    fun handleAction(action: CommentsOfUserAction) {
        when (action) {
            is CommentsOfUserAction.LoadComments -> loadData(action.userId)
            is CommentsOfUserAction.CommentsLoaded -> onDataLoaded(action.data)
            is CommentsOfUserAction.Error -> onError(action.error)
        }
    }

    private fun loadData(userId: Int) {
        _commentsOfUserState.value = _commentsOfUserState.value?.copy(isLoading = true)
        getCommentsByPostId(userId)
    }

    private fun onDataLoaded(data: List<CommentEntity>) {
        _commentsOfUserState.value = _commentsOfUserState.value?.copy(isLoading = false, data = data)
    }

    private fun onError(error: String) {
        _commentsOfUserState.value = _commentsOfUserState.value?.copy(isLoading = false, errorMessage = error)
    }

    private fun getCommentsByPostId(postId: Int) {
        viewModelScope.launch {
            try {
                val result = getPostCommentsUseCase.invoke(postId)
                onDataLoaded(result)
            } catch (e: Exception) {
                Log.e("CommentsOfUserViewModel",
                    "Error fetching comments | MESSAGE ${e.message} | CAUSE ${e.cause}"
                )
                onError(e.message.toString())
            }
        }
    }
}