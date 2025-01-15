package com.example.domain.entity

sealed class FeedItemEntity {
    data class PostItem(val postEntity: PostEntity) : FeedItemEntity()
    data class AlbumItem(val albumEntity: AlbumEntity) : FeedItemEntity()
}
