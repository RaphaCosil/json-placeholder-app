package com.example.domain.repository

import com.example.domain.entity.AlbumEntity
import com.example.domain.entity.CommentEntity
import com.example.domain.entity.PhotoEntity
import com.example.domain.entity.PostEntity
import com.example.domain.entity.UserEntity

interface AppRepository {
    suspend fun getFeedList(): List<Any>
    suspend fun getPostsByUserId(id: Int): List<PostEntity>
    suspend fun getPostComments(id: Int): List<CommentEntity>
    suspend fun createPost(post: PostEntity)
    suspend fun getAlbumsByUserId(id: Int): List<AlbumEntity>
    suspend fun createAlbum(album: AlbumEntity): AlbumEntity?
    suspend fun getPhotos(): List<PhotoEntity>
    suspend fun createPhoto(photo: PhotoEntity)
    suspend fun getAllUsers(): List<UserEntity>
    suspend fun getUserById(id: Int): UserEntity
}
