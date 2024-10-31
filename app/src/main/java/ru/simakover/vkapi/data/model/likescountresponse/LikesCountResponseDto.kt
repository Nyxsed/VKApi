package ru.simakover.vkapi.data.model.likescountresponse

import com.google.gson.annotations.SerializedName

data class LikesCountResponseDto(
    @SerializedName("response") val likes : LikesCountDto,
)
