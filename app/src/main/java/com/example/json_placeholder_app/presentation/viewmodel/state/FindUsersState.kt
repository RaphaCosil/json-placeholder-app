package com.example.json_placeholder_app.presentation.viewmodel.state

import com.example.domain.entity.UserEntity

data class FindUsersState (
    val isLoading: Boolean = false,
    var data: List<UserEntity> = emptyList(),
    val errorMessage: String? = null
)