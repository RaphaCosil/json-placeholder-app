package com.example.json_placeholder_app.data.repository

import android.util.Log
import com.example.json_placeholder_app.data.datasource.Service
import com.example.json_placeholder_app.data.model.toData
import com.example.json_placeholder_app.data.model.toEntity
import com.example.json_placeholder_app.domain.entity.AlbumEntity
import com.example.json_placeholder_app.domain.entity.CommentEntity
import com.example.json_placeholder_app.domain.entity.PhotoEntity
import com.example.json_placeholder_app.domain.entity.PostEntity
import com.example.json_placeholder_app.domain.entity.UserEntity
import com.example.json_placeholder_app.domain.repository.AppRepository
import java.util.Random

class AppRepositoryImpl(private val service: Service): AppRepository {
    override suspend fun getFeedList(): List<Any> {
        val albums = getAlbums()
        val posts = getAllPosts()
        val feedList = mutableListOf<Any>()
        feedList.addAll(albums)
        feedList.addAll(posts)
        feedList.shuffle(Random(System.currentTimeMillis()))
        Log.d("AppRepositoryImpl", feedList.toString())
        return feedList
    }

    override suspend fun getPostsByUserId(id: Int): List<PostEntity> {
        val posts = service.getPostsByUserId(id).body()?.map { it.toEntity() } ?: emptyList()
        for (post in posts) {
            post.userName = getUserById(post.userId).name
        }
        Log.d("AppRepositoryImpl", posts.toString())
        return posts
    }

    override suspend fun getPostComments(id: Int): List<CommentEntity> {
        return service.getPostComments(id).body()?.map { it.toEntity() } ?: emptyList()
    }

    override suspend fun createPost(post: PostEntity) {
        service.createPost(
            post.toData()
        )
    }

    override suspend fun getAlbumsByUserId(id: Int): List<AlbumEntity> {
        val albums = service.getAlbumsByUserId(id).body()?.map { it.toEntity() } ?: emptyList()
        for (album in albums) {
            album.userName = getUserById(album.userId).name
            album.photos = getPhotosByAlbumId(album.id.toString())
        }
        return albums
    }

    override suspend fun getAllUsers(): List<UserEntity> {
        return service.getUsers().body()?.map { it.toEntity() } ?: emptyList()
    }

    override suspend fun createAlbum(album: AlbumEntity) {
        service.createAlbum(
            album.toData()
        )
    }

    override suspend fun getUserById(id: Int): UserEntity {
        return service.getUserById(id).body()?.toEntity() ?: UserEntity(0, "", "", "")
    }

    private suspend fun getAlbums(): List<AlbumEntity> {
        val albums = service.getAlbums().body()?.map { it.toEntity() } ?: emptyList()
        for (album in albums) {
            album.userName = getUserById(album.userId).name
            album.photos = getPhotosByAlbumId(album.id.toString())
        }
        return albums
    }

    private suspend fun getAllPosts(): List<PostEntity> {
        val posts = service.getAllPosts().body()?.map { it.toEntity() } ?: emptyList()
        for (post in posts) {
            post.userName = getUserById(post.userId).name
        }
        return posts
    }

    private suspend fun getPhotosByAlbumId(id: String): List<PhotoEntity> {
        return service.getPhotosByAlbumId(id).body()?.map { it.toEntity() } ?: emptyList()
    }
}