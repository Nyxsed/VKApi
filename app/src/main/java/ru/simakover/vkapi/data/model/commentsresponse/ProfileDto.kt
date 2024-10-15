package ru.simakover.vkapi.data.model.commentsresponse


import com.google.gson.annotations.SerializedName

data class ProfileDto(
    @SerializedName("first_name") val firstName: String,
    @SerializedName("id") val id: Long,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("photo_100") val photo100: String
)