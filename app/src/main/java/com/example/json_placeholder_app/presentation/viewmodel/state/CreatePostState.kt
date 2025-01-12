package com.example.json_placeholder_app.presentation.viewmodel.state

data class CreatePostState (
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val errorMessage: String? = null
)