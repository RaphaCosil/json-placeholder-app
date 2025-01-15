package com.example.domain.usecase

import com.example.domain.repository.AppRepository

class GetFeedListUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(): List<Any> {
        return repository.getFeedList()
    }
}
