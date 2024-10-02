package ru.simakover.vkapi.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.simakover.vkapi.R
import ru.simakover.vkapi.ui.theme.VKApiTheme

@Composable
fun PostCard() {
    Card(
        modifier = Modifier
//            .background(MaterialTheme.colorScheme.primary)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Image(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(50.dp),
                painter = painterResource(id = R.drawable.post_comunity_thumbnail),
                contentDescription = null
            )
            Spacer(
                modifier = Modifier
                    .width(8.dp)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = "/dev/null",
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = "14:00",
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    modifier = Modifier,
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Group icon",
                    tint = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight"
)
@Composable
private fun PostCardPreview() {
    VKApiTheme {
        PostCard()
    }
}