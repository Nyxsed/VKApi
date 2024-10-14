package ru.simakover.vkapi.data.model

import com.google.gson.annotations.SerializedName

data class LikesCountResponseDto(
    @SerializedName("response") val likes : LikesCountDto,
)
