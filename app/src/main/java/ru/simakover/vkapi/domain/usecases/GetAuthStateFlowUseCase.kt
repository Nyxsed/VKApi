package ru.simakover.vkapi.domain.usecases

import kotlinx.coroutines.flow.StateFlow
import ru.simakover.vkapi.domain.entity.AuthState
import ru.simakover.vkapi.domain.repository.VkRepository

class GetAuthStateFlowUseCase(
    private val repository: VkRepository
) {
    operator fun invoke(): StateFlow<AuthState> {
        return repository.getAuthStateFlow()
    }
}