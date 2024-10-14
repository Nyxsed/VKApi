package ru.simakover.vkapi.domain.models

data class FeedPost(
    val id: String,
    val communityName: String,
    val publicationDate: Long,
    val communityImageUrl: String,
    val contentText: String,
    val contentImageUrl: String?,
    val statistics: List<StatisticItem>,
    val isLiked: Boolean
)