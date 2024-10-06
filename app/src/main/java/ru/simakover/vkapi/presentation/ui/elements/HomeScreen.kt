package ru.simakover.vkapi.presentation.ui.elements

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
import ru.simakover.vkapi.presentation.MainViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier,
) {
    val postList = viewModel.postList.observeAsState(listOf())

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
            items = postList.value,
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
                    onLikeClickListener = { statisticItem ->
                        viewModel.updateCount(
                            post = post,
                            item = statisticItem
                        )
                    },
                    onShareClickListener = { statisticItem ->
                        viewModel.updateCount(
                            post = post,
                            item = statisticItem
                        )
                    },
                    onViewsClickListener = { statisticItem ->
                        viewModel.updateCount(
                            post = post,
                            item = statisticItem
                        )
                    },
                    onCommentClickListener = { statisticItem ->
                        viewModel.updateCount(
                            post = post,
                            item = statisticItem
                        )
                    },
                )
            }
        }
    }
}