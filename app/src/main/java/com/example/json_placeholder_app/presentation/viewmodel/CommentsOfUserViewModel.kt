package com.example.json_placeholder_app.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.json_placeholder_app.domain.entity.CommentEntity
import com.example.json_placeholder_app.domain.usecase.GetPostCommentsUseCase
import kotlinx.coroutines.launch

class CommentsOfUserViewModel(
    private val getPostCommentsUseCase: GetPostCommentsUseCase
): ViewModel() {
    val comments = MutableLiveData<List<CommentEntity>>()
    fun getCommentsByPostId(postId: Int) {
        viewModelScope.launch {
            try {
                val result = getPostCommentsUseCase.invoke(postId)
                comments.value = result
            } catch (e: Exception) {
                Log.e("CommentsOfUserViewModel",
                    "Error fetching comments | MESSAGE ${e.message} | CAUSE ${e.cause}"
                )
            }
        }
    }
}