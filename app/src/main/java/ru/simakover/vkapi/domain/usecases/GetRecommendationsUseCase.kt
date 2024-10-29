package ru.simakover.vkapi.domain.usecases

import kotlinx.coroutines.flow.StateFlow
import ru.simakover.vkapi.domain.entity.FeedPost
import ru.simakover.vkapi.domain.repository.VkRepository

class GetRecommendationsUseCase(
    private val repository: VkRepository
) {
    operator fun invoke(): StateFlow<List<FeedPost>> {
        return repository.getRecommendations()
    }
}