package com.geekbrains.mydictionary.model.data

import com.google.gson.annotations.SerializedName

class Meanings(
    @field:SerializedName("translation") val translation: Translation?,
    @field:SerializedName("imageUrl") val imageUrl: String?,
    @field:SerializedName("soundUrl") val soundUrl: String?,
    @field:SerializedName("previewUrl") val previewUrl: String?
)