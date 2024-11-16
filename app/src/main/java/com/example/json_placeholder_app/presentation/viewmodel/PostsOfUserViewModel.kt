package com.example.json_placeholder_app.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.json_placeholder_app.domain.entity.PostEntity
import com.example.json_placeholder_app.domain.usecase.GetPostsByUserIdUseCase
import kotlinx.coroutines.launch

class PostsOfUserViewModel(
    private val getPostsByUserIdUseCase: GetPostsByUserIdUseCase
) : ViewModel() {
    var postList = MutableLiveData<List<PostEntity>>()
    fun getPostsByUserId(userId: Int) {
        viewModelScope.launch {
            try {
                val result = getPostsByUserIdUseCase.invoke(userId)
                postList.value = result
            } catch (e: Exception) {
                Log.e("PostsViewModel",
                    "Error fetching todos | MESSAGE ${e.message} | CAUSE ${e.cause}"
                )
            }
        }
    }
}