package com.example.json_placeholder_app.presentation.viewmodel.action

import com.example.domain.entity.FeedItemEntity

sealed class HomeAction {
    data object LoadData : HomeAction()
    data class DataLoaded(val data: List<FeedItemEntity>) : HomeAction()
    data class Error(val error: String) : HomeAction()
}