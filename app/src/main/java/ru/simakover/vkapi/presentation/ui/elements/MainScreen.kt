package ru.simakover.vkapi.presentation.ui.elements

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import ru.simakover.vkapi.presentation.ui.theme.VKApiTheme

@Composable
fun MainScreen() {

    val snackbarHostState = remember { SnackbarHostState() }
    val fabIsVisible = remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()

    Scaffold(
        bottomBar = {
            MainNavBar()
        },
        floatingActionButton = {
            if (fabIsVisible.value) {
                MainFab {
                    scope.launch {
                        val action = snackbarHostState.showSnackbar(
                            message = "This is snackbar",
                            actionLabel = "Hide FAB",
                            duration = SnackbarDuration.Long,
                        )
                        if (action == SnackbarResult.ActionPerformed) {
                            fabIsVisible.value = false
                        }
                    }
                }
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddings ->

    }
}


@Composable
fun MainFab(
    onFabClicked: () -> Unit,

) {
    FloatingActionButton(
        modifier = Modifier,
        onClick = {
            onFabClicked()
        }) {
        Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
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

@Preview
@Composable
private fun MainScreenPreview() {
    VKApiTheme {
        MainScreen()
    }
}