package com.example.json_placeholder_app.data.datasource

import com.example.json_placeholder_app.data.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {
    @GET("posts")
    suspend fun getAllPosts(
        @Query("_limit") limit: Int = 20
    ): Response<List<PostDAO>>

    @GET("posts")
    suspend fun getPostsByUserId(
        @Query("userId") userId: Int
    ): Response<List<PostDAO>>

    @POST("posts/")
    suspend fun createPost(@Body post: PostDAO)

    @GET("posts/{id}/comments/")
    suspend fun getPostComments(
        @Path("id") id: Int
    ): Response<List<CommentDAO>>

    @GET("albums")
    suspend fun getAlbums(
        @Query("_limit") limit: Int = 8
    ): Response<List<AlbumDAO>>

    @POST("albums/")
    suspend fun createAlbum(@Body album: AlbumDAO)

    @GET("albums")
    suspend fun getAlbumsByUserId(
        @Query("userId") userId: Int
    ): Response<List<AlbumDAO>>

    @GET("photos")
    suspend fun getPhotosByAlbumId(
        @Query("albumId") albumId: String,
        @Query("_limit") limit: Int = 2
    ): Response<List<PhotoDAO>>

    @GET("users/")
    suspend fun getUsers(): Response<List<UserDAO>>

    @GET("users/{id}")
    suspend fun getUserById(
        @Path("id") id: Int
    ): Response<UserDAO>
}
