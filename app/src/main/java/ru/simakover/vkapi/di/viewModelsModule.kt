package ru.simakover.vkapi.di


import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.simakover.vkapi.domain.usecases.ChangeLikeStatusUseCase
import ru.simakover.vkapi.domain.usecases.CheckAuthUseCase
import ru.simakover.vkapi.domain.usecases.DeletePostUseCase
import ru.simakover.vkapi.domain.usecases.GetAuthStateFlowUseCase
import ru.simakover.vkapi.domain.usecases.GetCommentsUseCase
import ru.simakover.vkapi.domain.usecases.GetRecommendationsUseCase
import ru.simakover.vkapi.domain.usecases.LoadNextDataUseCase
import ru.simakover.vkapi.presentation.screens.comments.CommentsViewModel
import ru.simakover.vkapi.presentation.screens.main.MainViewModel
import ru.simakover.vkapi.presentation.screens.newsfeed.NewsFeedViewModel

val viewModelsModule = module {
    viewModel<NewsFeedViewModel> {
        NewsFeedViewModel(
            getRecommendationsUseCase = GetRecommendationsUseCase(get()),
            loadNextDataUseCase = LoadNextDataUseCase(get()),
            changeLikeStatusUseCase = ChangeLikeStatusUseCase(get()),
            deletePostUseCase = DeletePostUseCase(get())
        )
    }

    viewModel<MainViewModel> {
        MainViewModel(
            getAuthStateFlowUseCase = GetAuthStateFlowUseCase(get()),
            checkAuthStateUseCase = CheckAuthUseCase(get())
        )
    }

    viewModel<CommentsViewModel> {
        CommentsViewModel(
            feedPost = get(),
            getCommentsScreenState = GetCommentsUseCase(get())
        )
    }
}