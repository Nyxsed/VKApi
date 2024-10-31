package ru.simakover.vkapi.di

import com.vk.api.sdk.VKKeyValueStorage
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import org.koin.dsl.module
import ru.simakover.vkapi.data.mapper.VkMapper
import ru.simakover.vkapi.data.network.ApiFactory
import ru.simakover.vkapi.data.network.ApiService
import ru.simakover.vkapi.data.repository.VkRepositoryImpl
import ru.simakover.vkapi.domain.repository.VkRepository


val dataModule = module {
    single<ApiService> {
        ApiFactory.apiService
    }

    single<VkMapper> {
        VkMapper()
    }

    single<VKKeyValueStorage> {
        VKPreferencesKeyValueStorage(
            context = get()
        )
    }

    single<VkRepository> {
        VkRepositoryImpl(
            apiService = get(),
            mapper = get(),
            storage = get()
        )
    }
}