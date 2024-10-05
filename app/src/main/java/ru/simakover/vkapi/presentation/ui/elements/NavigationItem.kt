package ru.simakover.vkapi.presentation.ui.elements

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import ru.simakover.vkapi.R

sealed class NavigationItem(
    val titleResId: Int,
    val icon: ImageVector,
) {

    object Home : NavigationItem(
        titleResId = R.string.navigation_item_main,
        icon = Icons.Outlined.Home
    )

    object Favourites : NavigationItem(
        titleResId = R.string.navigation_item_favourites,
        icon = Icons.Outlined.Favorite
    )

    object Profile : NavigationItem(
        titleResId = R.string.navigation_item_profile,
        icon = Icons.Outlined.AccountCircle
    )

}