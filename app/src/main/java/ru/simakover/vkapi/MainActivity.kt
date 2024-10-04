package ru.simakover.vkapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            VKApiTheme {
//                Box(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(MaterialTheme.colorScheme.primary)
//                        .padding(8.dp),
//                ) {
//                    PostCard()
//                }
            ScaffoldExample()
//            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ScaffoldExample() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Top Bar example")
                },
                colors = TopAppBarColors(
                    containerColor = Color.Red,
                    scrolledContainerColor = Color.Red,
                    navigationIconContentColor = Color.Red,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.Red,
                ),
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = null, tint = Color.White)
                    }
                }
            )
        },
        bottomBar = {
//            var selectedItem by remember { mutableStateOf(0) }
            val items = listOf("Songs", "Artists", "Playlists")
            NavigationBar(
                windowInsets = NavigationBarDefaults.windowInsets
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                        label = { Text(item) },
                        selected = true,
                        onClick = {  }
                    )
                }
            }
        },
    ) {
        Text(
            modifier = Modifier
                .padding(it),
            text = "Scaffold example text"
        )
    }
}
