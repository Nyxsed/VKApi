package ru.simakover.vkapi.domain.models

import ru.simakover.vkapi.R

data class PostComment(
    val id: Int,
    val authorName: String = "Author",
    val authorAvatarId: Int = R.drawable.ic_avatar,
    val commentText: String = "Long comment text",
    val publicationDate: String = "14:00",
)