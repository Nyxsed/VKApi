package ru.simakover.vkapi.data.mapper

import ru.simakover.vkapi.data.model.commentsresponse.CommentsResponseDto
import ru.simakover.vkapi.data.model.newsfeedresponse.NewsFeedResponseDto
import ru.simakover.vkapi.domain.models.FeedPost
import ru.simakover.vkapi.domain.models.PostComment
import ru.simakover.vkapi.domain.models.StatisticItem
import ru.simakover.vkapi.domain.models.StatisticType
import kotlin.math.absoluteValue

class VkMapper {

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
                communityId = post.communityId,
                publicationDate = post.date * 1000,
                communityImageUrl = group.imageUrl,
                contentText = post.text,
                contentImageUrl = post.attachment?.firstOrNull()?.photo?.photoUrls?.lastOrNull()?.url,
                isLiked = post.likes.userLikes > 0,
                statistics = listOf(
                    StatisticItem(type = StatisticType.LIKES, post.likes.count),
                    StatisticItem(type = StatisticType.VIEWS, post.views.count),
                    StatisticItem(type = StatisticType.COMMENTS, post.comments.count),
                    StatisticItem(type = StatisticType.SHARES, post.reposts.count),
                ),
            )

            result.add(feedPost)
        }

        return result
    }

    fun mapResponseToComments(responseDto: CommentsResponseDto): List<PostComment> {
        val result = mutableListOf<PostComment>()

        val comments = responseDto.response.comments
        val profiles = responseDto.response.profiles

        for (comment in comments) {

            val author = profiles.find {
                it.id == comment.fromId
            } ?: continue

            if (comment.text.isBlank()) continue

            val postComment = PostComment(
                id = comment.id,
                authorName = "${author.firstName} ${author.lastName}",
                authorAvatarUrl = author.photo100,
                commentText = comment.text,
                publicationDate = comment.date * 1000
            )

            result.add(postComment)
        }

        return result
    }
}