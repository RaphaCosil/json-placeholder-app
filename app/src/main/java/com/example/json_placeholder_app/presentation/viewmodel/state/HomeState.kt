package com.example.json_placeholder_app.presentation.viewmodel.state

import com.example.domain.entity.FeedItemEntity

data class HomeState (
    val isLoading: Boolean = false,
    var data: List<FeedItemEntity> = emptyList(),
    val errorMessage: String? = null
)