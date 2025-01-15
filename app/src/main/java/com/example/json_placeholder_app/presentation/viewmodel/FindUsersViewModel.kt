package com.example.json_placeholder_app.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.UserEntity
import com.example.domain.usecase.GetAllUsersUseCase
import com.example.json_placeholder_app.presentation.viewmodel.action.FindUsersAction
import com.example.json_placeholder_app.presentation.viewmodel.state.FindUsersState
import kotlinx.coroutines.launch

class FindUsersViewModel(
    private val getAllUsersUseCase: GetAllUsersUseCase
) : ViewModel() {
    private val _findUsersState = MutableLiveData<FindUsersState>()
    val findUsersState: LiveData<FindUsersState> = _findUsersState

    init {
        _findUsersState.value = FindUsersState()
    }

    fun handleAction(action: FindUsersAction) {
        when (action) {
            is FindUsersAction.LoadUsers -> getAllUsers()
            is FindUsersAction.UsersLoaded -> onUsersLoaded(action.data)
            is FindUsersAction.Error -> onError(action.error)
        }
    }

    private fun onUsersLoaded(users: List<UserEntity>) {
        _findUsersState.value = _findUsersState.value?.copy(data = users)
    }

    private fun onError(error: String) {
        _findUsersState.value = _findUsersState.value?.copy(errorMessage = error)
    }

    fun getAllUsers() {
        viewModelScope.launch {
            try {
                val result = getAllUsersUseCase.invoke()
                onUsersLoaded(result)
            } catch (e: Exception) {
                Log.e(
                    "PostsViewModel",
                    "Error fetching todos | MESSAGE ${e.message} | CAUSE ${e.cause}"
                )
                onError(e.message.toString())
            }
        }
    }
}