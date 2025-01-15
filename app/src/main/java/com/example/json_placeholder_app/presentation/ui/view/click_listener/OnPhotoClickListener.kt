package com.example.json_placeholder_app.presentation.ui.view.click_listener

import com.example.json_placeholder_app.domain.entity.PhotoEntity

interface OnPhotoClickListener {
    fun onPhotoClick(photoEntity: PhotoEntity)
}