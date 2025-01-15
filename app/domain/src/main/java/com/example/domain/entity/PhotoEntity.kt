package com.example.domain.entity

data class PhotoEntity(
    var albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)
