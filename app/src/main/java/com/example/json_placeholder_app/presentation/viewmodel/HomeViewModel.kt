package com.example.json_placeholder_app.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.json_placeholder_app.domain.entity.FeedItemEntity
import com.example.json_placeholder_app.domain.usecase.GetFeedListUseCase
import com.example.json_placeholder_app.presentation.utils.FeedItemEntityDeserializer
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getFeedListUseCase: GetFeedListUseCase
) : ViewModel() {
     val feedItemList = MutableLiveData<List<FeedItemEntity>>()
    fun getFeedList() {
        viewModelScope.launch {
            try {
                val result = getFeedListUseCase.invoke()
                val gson = GsonBuilder()
                    .registerTypeAdapter(FeedItemEntity::class.java, FeedItemEntityDeserializer())
                    .create()
                val jsonElement = gson.toJson(result)
                feedItemList.value = gson.fromJson(jsonElement, Array<FeedItemEntity>::class.java).toList()
            } catch (e: Exception) {
                Log.e("PostsViewModel",
                    "Error fetching todos | MESSAGE ${e.message} | CAUSE ${e.cause}"
                )
            }
        }
    }
}