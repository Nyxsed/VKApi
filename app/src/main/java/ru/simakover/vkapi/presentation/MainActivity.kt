package ru.simakover.vkapi.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope
import ru.simakover.vkapi.presentation.ui.screens.MainScreen
import ru.simakover.vkapi.presentation.ui.screens.login.AuthState
import ru.simakover.vkapi.presentation.ui.screens.login.LoginScreen
import ru.simakover.vkapi.presentation.ui.theme.VKApiTheme
import ru.simakover.vkapi.presentation.viewmodels.MainViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            VKApiTheme {
                val viewModel: MainViewModel = viewModel()
                val authState = viewModel.authState.observeAsState(AuthState.Initial)

                val launcher = rememberLauncherForActivityResult(
                    contract = VK.getVKAuthActivityResultContract()
                ) {
                    viewModel.performAuthResult(it)
                }

                when (authState.value) {
                    is AuthState.Authorized -> {
                        MainScreen()
                    }

                    AuthState.NotAuthorized ->
                        LoginScreen(
                            onLoginClick = {
                                launcher.launch(listOf(VKScope.WALL))
                            }
                        )

                    AuthState.Initial -> {}
                }
            }
        }
    }
}