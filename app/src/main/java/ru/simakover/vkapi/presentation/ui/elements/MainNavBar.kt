package ru.simakover.vkapi.presentation.ui.elements

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.simakover.vkapi.presentation.models.NavigationItem

@Composable
fun MainNavBar(
    currentRoute: String?,
    navItemOnClickListener: (NavigationItem) -> Unit,
) {
    NavigationBar {
        val items = listOf(
            NavigationItem.Home,
            NavigationItem.Favourites,
            NavigationItem.Profile
        )
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.screen.route,
                onClick = {
                    navItemOnClickListener(item)
                },
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