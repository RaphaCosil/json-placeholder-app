package com.example.json_placeholder_app.presentation.viewmodel.action

import com.example.json_placeholder_app.domain.entity.UserEntity

sealed class FindUsersAction {
    data object LoadUsers : FindUsersAction()
    data class UsersLoaded(val data: List<UserEntity>): FindUsersAction()
    data class Error(val error: String): FindUsersAction()
}