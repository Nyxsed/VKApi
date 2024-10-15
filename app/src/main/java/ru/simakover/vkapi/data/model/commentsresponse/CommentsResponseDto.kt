package ru.simakover.vkapi.data.model.commentsresponse


import com.google.gson.annotations.SerializedName

data class CommentsResponseDto(
    @SerializedName("response") val response: CommentsContentDto
)