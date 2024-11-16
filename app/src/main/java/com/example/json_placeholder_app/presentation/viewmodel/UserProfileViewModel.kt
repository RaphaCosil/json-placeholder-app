package com.example.json_placeholder_app.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.json_placeholder_app.domain.entity.UserEntity
import com.example.json_placeholder_app.domain.usecase.GetAlbumsByUserIdUseCase
import com.example.json_placeholder_app.domain.usecase.GetAllUsersUseCase
import com.example.json_placeholder_app.domain.usecase.GetUserByIdUseCase
import kotlinx.coroutines.launch

class UserProfileViewModel(
    private val getUserByIdUseCase: GetUserByIdUseCase
) : ViewModel() {
    var users = MutableLiveData<UserEntity>()
    fun getUser(userId: Int) {
        viewModelScope.launch {
            try {
                val result = getUserByIdUseCase.invoke(userId)
                users.value = result
            } catch (e: Exception) {
                Log.e(
                    "PostsViewModel",
                    "Error fetching todos | MESSAGE ${e.message} | CAUSE ${e.cause}"
                )
            }
        }
    }
}