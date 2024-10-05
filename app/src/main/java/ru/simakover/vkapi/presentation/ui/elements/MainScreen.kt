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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.simakover.vkapi.presentation.MainViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel,
) {
    val postList = viewModel.postList.observeAsState(listOf())

    Scaffold(
        bottomBar = {
            MainNavBar()
        },
    ) { paddings ->
        LazyColumn(
            modifier = Modifier
                .padding(paddings),
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
                        //reference, same as viewModel.updateCount(it)
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
}

@Composable
fun MainNavBar() {
    NavigationBar {

        var selectedItemPosition = remember { mutableStateOf(0) }

        val items = listOf(
            NavigationItem.Home,
            NavigationItem.Favourites,
            NavigationItem.Profile
        )
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemPosition.value == index,
                onClick = { selectedItemPosition.value = index },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = null)
                },
                label = {
                    Text(stringResource(id = item.titleResId))
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedTextColor = MaterialTheme.colorScheme.onSecondary,
                )
            )
        }
    }
}

//@Preview
//@Composable
//private fun MainScreenPreview() {
//    VKApiTheme {
//        MainScreen()
//    }
//}