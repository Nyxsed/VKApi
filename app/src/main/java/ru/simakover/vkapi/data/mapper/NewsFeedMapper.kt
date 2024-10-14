package ru.simakover.vkapi.data.mapper

import ru.simakover.vkapi.data.model.NewsFeedResponseDto
import ru.simakover.vkapi.domain.models.FeedPost
import ru.simakover.vkapi.domain.models.StatisticItem
import ru.simakover.vkapi.domain.models.StatisticType
import kotlin.math.absoluteValue

class NewsFeedMapper {

    fun mapResponseToPosts(responseDto: NewsFeedResponseDto): List<FeedPost> {
        val result = mutableListOf<FeedPost>()

        val posts = responseDto.newsFeedContent.posts
        val groups = responseDto.newsFeedContent.groups

        for (post in posts) {

            // только посты от групп и поиск по модулю - в группах ид с минусом, в постах нет
            val group = groups.find {
                it.id == post.communityId.absoluteValue
            } ?: continue

            val feedPost = FeedPost(
                id = post.id,
                communityName = group.name,
                publicationDate = post.date * 1000,
                communityImageUrl = group.imageUrl,
                contentText = post.text,
                contentImageUrl = post.attachment?.firstOrNull()?.photo?.photoUrls?.lastOrNull()?.url,
                statistics = listOf(
                    StatisticItem(type = StatisticType.LIKES, post.likes.count),
                    StatisticItem(type = StatisticType.VIEWS, post.views.count),
                    StatisticItem(type = StatisticType.COMMENTS, post.comments.count),
                    StatisticItem(type = StatisticType.SHARES, post.reposts.count),
                ),
                isLiked = post.isFavourite
            )

            result.add(feedPost)
        }

        return result
    }
}