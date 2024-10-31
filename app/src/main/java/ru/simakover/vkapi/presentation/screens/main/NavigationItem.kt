package ru.simakover.vkapi.presentation.screens.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import ru.simakover.vkapi.R
import ru.simakover.vkapi.navigation.Screen

sealed class NavigationItem(
    val titleResId: Int,
    val icon: ImageVector,
    val screen: Screen,
) {

    object Home : NavigationItem(
        titleResId = R.string.navigation_item_main,
        icon = Icons.Outlined.Home,
        screen = Screen.Home,
    )

    object Favourites : NavigationItem(
        titleResId = R.string.navigation_item_favourites,
        icon = Icons.Outlined.Favorite,
        screen = Screen.Favourite,
    )

    object Profile : NavigationItem(
        titleResId = R.string.navigation_item_profile,
        icon = Icons.Outlined.AccountCircle,
        screen = Screen.Profile,
    )

}