package com.example.domain.entity

data class PostEntity(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
    var userName: String
)
