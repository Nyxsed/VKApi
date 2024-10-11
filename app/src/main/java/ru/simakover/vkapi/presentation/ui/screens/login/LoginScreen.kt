package ru.simakover.vkapi.presentation.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.simakover.vkapi.R
import ru.simakover.vkapi.presentation.ui.theme.VkBlue

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 200.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Image(
            modifier = Modifier
                .size(150.dp),
            painter = painterResource(id = R.drawable.vk_logo),
            contentDescription = null
        )
        Button(
            colors = ButtonDefaults.buttonColors(
            containerColor = VkBlue,
            contentColor =  Color.White
        ),
            onClick = {
                onLoginClick()
            }) {
            Text(text = stringResource(R.string.LogIn))
        }
    }
}