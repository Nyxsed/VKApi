package ru.simakover.vkapi.data.model

import com.google.gson.annotations.SerializedName

data class PhotoDto (
    @SerializedName("sizes") val photoUrls: List<PhotoUrlDto>
)