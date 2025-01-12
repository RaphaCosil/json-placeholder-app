package com.example.json_placeholder_app.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.json_placeholder_app.domain.entity.UserEntity
import com.example.json_placeholder_app.domain.usecase.GetUserByIdUseCase
import com.example.json_placeholder_app.presentation.viewmodel.action.UserProfileAction
import com.example.json_placeholder_app.presentation.viewmodel.state.UserProfileState
import kotlinx.coroutines.launch

class UserProfileViewModel(
    private val getUserByIdUseCase: GetUserByIdUseCase
) : ViewModel() {
    private val _userProfileState = MutableLiveData<UserProfileState>()
    val userProfileState: LiveData<UserProfileState> = _userProfileState

    init {
        _userProfileState.value = UserProfileState()
    }

    fun handleAction(action: UserProfileAction) {
        when (action) {
            is UserProfileAction.LoadUserProfile -> getUser(action.userId)
            is UserProfileAction.UserProfileLoaded -> onUserProfileLoaded(action.data)
            is UserProfileAction.Error -> onError(action.error)
        }
    }

    private fun onUserProfileLoaded(user: UserEntity) {
        _userProfileState.value = _userProfileState.value?.copy(data = user)
    }

    private fun onError(error: String) {
        _userProfileState.value = _userProfileState.value?.copy(errorMessage = error)
    }


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