package ru.simakover.vkapi.domain.usecases

import ru.simakover.vkapi.domain.entity.FeedPost
import ru.simakover.vkapi.domain.repository.VkRepository

class ChangeLikeStatusUseCase(
    private val repository: VkRepository
) {
    suspend operator fun invoke(feedPost: FeedPost) {
        repository.changeLikeStatus(feedPost)
    }
}