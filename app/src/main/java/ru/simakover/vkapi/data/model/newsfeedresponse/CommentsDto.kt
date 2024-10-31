package ru.simakover.vkapi.data.model.newsfeedresponse

import com.google.gson.annotations.SerializedName

data class CommentsDto(
    @SerializedName("count") val count: Int,
)
