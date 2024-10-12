package ru.simakover.vkapi.domain.models

data class FeedPost(
    val id: String,
    val communityName: String,
    val publicationDate: String,
    val communityImageUrl: String,
    val contentText: String,
    val contentImageUrl: String?,
    var statistics: List<StatisticItem>,
)