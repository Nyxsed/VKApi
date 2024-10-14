package ru.simakover.vkapi.domain.models

data class FeedPost(
    val id: Long,
    val communityName: String,
    val communityId: Long,
    val publicationDate: Long,
    val communityImageUrl: String,
    val contentText: String,
    val contentImageUrl: String?,
    val statistics: List<StatisticItem>,
    val isLiked: Boolean
)