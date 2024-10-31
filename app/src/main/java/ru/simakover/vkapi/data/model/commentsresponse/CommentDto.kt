package ru.simakover.vkapi.data.model.commentsresponse


import com.google.gson.annotations.SerializedName

data class CommentDto(
    @SerializedName("date") val date: Long,
    @SerializedName("from_id") val fromId: Long,
    @SerializedName("id") val id: Long,
    @SerializedName("text") val text: String
)