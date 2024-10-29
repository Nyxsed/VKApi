package ru.simakover.vkapi.domain.usecases

import kotlinx.coroutines.flow.StateFlow
import ru.simakover.vkapi.domain.entity.FeedPost
import ru.simakover.vkapi.domain.entity.PostComment
import ru.simakover.vkapi.domain.repository.VkRepository

class GetCommentsUseCase(
    private val repository: VkRepository,
) {
    operator fun invoke(feedPost: FeedPost): StateFlow<List<PostComment>> {
        return repository.getComments(feedPost)
    }
}