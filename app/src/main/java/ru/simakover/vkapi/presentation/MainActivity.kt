package ru.simakover.vkapi.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.simakover.vkapi.presentation.ui.elements.MainScreen
import ru.simakover.vkapi.presentation.ui.elements.PostCard
import ru.simakover.vkapi.presentation.ui.theme.VKApiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VKApiTheme {
//                Box(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(MaterialTheme.colorScheme.primary)
//                        .padding(8.dp),
//                ) {
//                    PostCard()
//                }
                MainScreen()
            }
        }
    }
}