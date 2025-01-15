package com.example.json_placeholder_app.presentation.viewmodel.action

import com.example.domain.entity.UserEntity

sealed class UserProfileAction {
    data class LoadUserProfile(val userId: Int): UserProfileAction()
    data class UserProfileLoaded(val data: UserEntity): UserProfileAction()
    data class Error(val error: String): UserProfileAction()
}