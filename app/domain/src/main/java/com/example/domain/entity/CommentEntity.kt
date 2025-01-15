package com.example.domain.entity

data class CommentEntity(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
)