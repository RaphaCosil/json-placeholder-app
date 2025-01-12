package com.example.json_placeholder_app.presentation.viewmodel.state

import com.example.json_placeholder_app.domain.entity.UserEntity

data class UserProfileState (
    val isLoading: Boolean = false,
    var data: UserEntity? = null,
    val errorMessage: String? = null
)