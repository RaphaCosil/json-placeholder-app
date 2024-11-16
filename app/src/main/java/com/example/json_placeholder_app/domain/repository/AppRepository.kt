package com.example.json_placeholder_app.domain.repository

import com.example.json_placeholder_app.domain.entity.AlbumEntity
import com.example.json_placeholder_app.domain.entity.CommentEntity
import com.example.json_placeholder_app.domain.entity.PostEntity
import com.example.json_placeholder_app.domain.entity.UserEntity

interface AppRepository {
    suspend fun getFeedList(): List<Any>
    suspend fun getPostsByUserId(id: Int): List<PostEntity>
    suspend fun getPostComments(id: Int): List<CommentEntity>
    suspend fun createPost(post: PostEntity)
    suspend fun getAlbumsByUserId(id: Int): List<AlbumEntity>
    suspend fun createAlbum(album: AlbumEntity)
    suspend fun getAllUsers(): List<UserEntity>
    suspend fun getUserById(id: Int): UserEntity
}
