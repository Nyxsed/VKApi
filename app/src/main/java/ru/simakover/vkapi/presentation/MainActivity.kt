package ru.simakover.vkapi.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import ru.simakover.vkapi.presentation.ui.screens.MainScreen
import ru.simakover.vkapi.presentation.ui.theme.VKApiTheme
import ru.simakover.vkapi.presentation.viewmodels.NewsFeedViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            VKApiTheme {
                MainScreen()
            }
        }
    }
}