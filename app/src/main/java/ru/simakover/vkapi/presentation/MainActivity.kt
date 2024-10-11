package ru.simakover.vkapi.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.compose.runtime.SideEffect
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAuthenticationResult
import com.vk.api.sdk.auth.VKScope
import ru.simakover.vkapi.ActivityResultTest
import ru.simakover.vkapi.presentation.ui.theme.VKApiTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            VKApiTheme {

                val launcher = rememberLauncherForActivityResult(contract =VK.getVKAuthActivityResultContract()) {
                    when (it) {
                        is VKAuthenticationResult.Success -> {
                            Log.d("MainActivity", "successful authentication")
                        }

                        is VKAuthenticationResult.Failed -> {
                            Log.d("MainActivity", "failed authentication")
                        }
                    }
                }
                SideEffect {
                    launcher.launch(listOf(VKScope.WALL))
                }
//                MainScreen()
                ActivityResultTest()
            }
        }
    }
}