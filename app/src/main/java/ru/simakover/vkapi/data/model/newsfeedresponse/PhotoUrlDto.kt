package ru.simakover.vkapi.data.model.newsfeedresponse

import com.google.gson.annotations.SerializedName

data class PhotoUrlDto(
    @SerializedName("url") val url: String,
    @SerializedName("type") val type: String,
)
