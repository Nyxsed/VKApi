package ru.simakover.vkapi.presentation.ui.elements

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.simakover.vkapi.domain.PostItem
import ru.simakover.vkapi.presentation.MainViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel,
) {
    val post = viewModel.postItem.observeAsState(PostItem())

    Scaffold(
        bottomBar = {
            MainNavBar()
        },
    ) { paddings ->
        PostCard(
            modifier = Modifier
                .padding(8.dp),
            post = post.value,
            //reference, same as viewModel.updateCount(it)
            onLikeClickListener = viewModel::updateCount,
            onShareClickListener = viewModel::updateCount,
            onViewsClickListener = viewModel::updateCount,
            onCommentClickListener = viewModel::updateCount,
        )
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