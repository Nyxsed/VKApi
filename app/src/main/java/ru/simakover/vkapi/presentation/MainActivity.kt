package ru.simakover.vkapi.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.simakover.vkapi.ActivityResultTest
import ru.simakover.vkapi.presentation.ui.theme.VKApiTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            VKApiTheme {
//                MainScreen()
                ActivityResultTest()
            }
        }
    }
}