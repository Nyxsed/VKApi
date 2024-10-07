package ru.simakover.vkapi.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import ru.simakover.vkapi.presentation.ui.screens.MainScreen
import ru.simakover.vkapi.presentation.ui.theme.VKApiTheme
import ru.simakover.vkapi.presentation.viewmodels.MainViewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            VKApiTheme {
                MainScreen(
                    viewModel = viewModel
                )
            }
        }
    }
}