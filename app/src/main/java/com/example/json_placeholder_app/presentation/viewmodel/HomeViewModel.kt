package com.example.json_placeholder_app.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.json_placeholder_app.domain.entity.FeedItemEntity
import com.example.json_placeholder_app.domain.usecase.GetFeedListUseCase
import com.example.json_placeholder_app.presentation.utils.FeedItemEntityDeserializer
import com.example.json_placeholder_app.presentation.viewmodel.action.HomeAction
import com.example.json_placeholder_app.presentation.viewmodel.state.HomeState
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getFeedListUseCase: GetFeedListUseCase
) : ViewModel() {
    private val _homeState = MutableLiveData<HomeState>()
    val homeState: LiveData<HomeState> get() = _homeState

    init {
        _homeState.value = HomeState()
    }

    fun handleAction(action: HomeAction) {
        when (action) {
            is HomeAction.LoadData -> loadData()
            is HomeAction.DataLoaded -> onDataLoaded(action.data)
            is HomeAction.Error -> onError(action.error)
        }
    }

    private fun loadData() {
        _homeState.value = _homeState.value?.copy(isLoading = true)
        getFeedList()
    }

    private fun getFeedList() {
        viewModelScope.launch {
            try {
                val result = getFeedListUseCase.invoke()
                val gson = GsonBuilder()
                    .registerTypeAdapter(FeedItemEntity::class.java, FeedItemEntityDeserializer())
                    .create()
                val jsonElement = gson.toJson(result)
                onDataLoaded(
                    gson.fromJson(jsonElement, Array<FeedItemEntity>::class.java).toList()
                )
            } catch (e: Exception) {
                onError(e.message ?: "An error occurred")
            }
        }
    }

    private fun onDataLoaded(feedItemEntityList: List<FeedItemEntity>) {
        _homeState.value = _homeState.value?.copy(isLoading = false, data = feedItemEntityList)
    }

    private fun onError(error: String) {
        _homeState.value = _homeState.value?.copy(isLoading = false, errorMessage = error)
    }
}
