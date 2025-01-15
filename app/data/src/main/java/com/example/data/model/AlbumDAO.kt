package com.example.data.model

import com.example.domain.entity.AlbumEntity

data class AlbumDAO (
    val userId: Int,
    val id: Int,
    val title: String
)

fun AlbumEntity.toData(): AlbumDAO {
    return AlbumDAO(
        userId = userId,
        id = id,
        title = title
    )
}

fun AlbumDAO.toEntity(): AlbumEntity {
    return AlbumEntity(
        userId = userId,
        id = id,
        title = title,
        photos = emptyList(),
        userName = ""
    )
}
