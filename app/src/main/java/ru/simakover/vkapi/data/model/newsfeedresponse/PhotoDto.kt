package ru.simakover.vkapi.data.model.newsfeedresponse

import com.google.gson.annotations.SerializedName

data class PhotoDto (
    @SerializedName("sizes") val photoUrls: List<PhotoUrlDto>
)