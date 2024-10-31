package ru.simakover.vkapi.data.model.likescountresponse

import com.google.gson.annotations.SerializedName

data class LikesCountDto(
    @SerializedName("likes") val count : Int,
)