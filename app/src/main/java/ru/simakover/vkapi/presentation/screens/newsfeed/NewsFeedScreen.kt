package ru.simakover.vkapi.presentation.screens.newsfeed

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.simakover.vkapi.domain.models.FeedPost


@Composable
fun NewsFeedScreen(
    modifier: Modifier = Modifier,
    onCommentClickListener: (FeedPost) -> Unit,
) {
    val viewModel: NewsFeedViewModel = viewModel()

    val screenState = viewModel.screenState.observeAsState(NewsFeedScreenState.Initial)
    val currentState = screenState.value

    when (currentState) {
        is NewsFeedScreenState.Posts -> {
            FeedPosts(
                posts = currentState.posts,
                modifier = modifier,
                viewModel = viewModel,
                onCommentClickListener = onCommentClickListener
            )
        }

        NewsFeedScreenState.Initial -> {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun FeedPosts(
    posts: List<FeedPost>,
    modifier: Modifier,
    viewModel: NewsFeedViewModel,
    onCommentClickListener: (FeedPost) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(
            top = 8.dp,
            start = 8.dp,
            end = 8.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = posts,
            key = { it.id }
        ) { post ->
            val dismissBoxState = rememberSwipeToDismissBoxState(
                positionalThreshold = { it * .75f }
            )

            if (dismissBoxState.currentValue == SwipeToDismissBoxValue.EndToStart) {
                viewModel.deletePost(post)
            }

            SwipeToDismissBox(
                modifier = Modifier.animateItemPlacement(),
                state = dismissBoxState,
                enableDismissFromStartToEnd = false,
                backgroundContent = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .background(Color.Red.copy(alpha = 0.5f)),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Text(
                            text = "Delete Post",
                            color = Color.White,
                            fontSize = 24.sp,
                            modifier = Modifier
                                .padding(16.dp)
                        )
                    }
                }
            ) {
                PostCard(
                    post = post,
                    onLikeClickListener = {
                        viewModel.changeLikeStatus(post = post)
                    },
                    onShareClickListener = { statisticItem ->
                        viewModel.updateCount(
                            feedPost = post,
                            item = statisticItem
                        )
                    },
                    onViewsClickListener = { statisticItem ->
                        viewModel.updateCount(
                            feedPost = post,
                            item = statisticItem
                        )
                    },
                    onCommentClickListener = { statisticItem ->
                        onCommentClickListener(post)
                    },
                )
            }
        }
    }
}