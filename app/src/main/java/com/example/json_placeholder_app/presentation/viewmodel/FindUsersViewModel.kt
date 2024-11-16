package com.example.json_placeholder_app.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.json_placeholder_app.domain.entity.UserEntity
import com.example.json_placeholder_app.domain.usecase.GetAllUsersUseCase
import kotlinx.coroutines.launch

class FindUsersViewModel(
    private val getAllUsersUseCase: GetAllUsersUseCase
) : ViewModel() {
    var users = MutableLiveData<List<UserEntity>>()
    fun getAllUsers() {
        viewModelScope.launch {
            try {
                val result = getAllUsersUseCase.invoke()
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