package com.example.json_placeholder_app.presentation.utils

import com.example.domain.entity.AlbumEntity
import com.example.domain.entity.FeedItemEntity
import com.example.domain.entity.PostEntity
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

class FeedItemEntityDeserializer : JsonDeserializer<FeedItemEntity> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): FeedItemEntity {
        val jsonObject = json.asJsonObject

        return when {
            jsonObject.has("body") -> {
                val postEntity = context.deserialize<PostEntity>(jsonObject, PostEntity::class.java)
                FeedItemEntity.PostItem(postEntity)
            }
            jsonObject.has("photos") -> {
                val albumEntity = context.deserialize<AlbumEntity>(jsonObject, AlbumEntity::class.java)
                FeedItemEntity.AlbumItem(albumEntity)
            }
            else -> throw JsonParseException("Unknown FeedItemEntity type")
        }
    }
}
