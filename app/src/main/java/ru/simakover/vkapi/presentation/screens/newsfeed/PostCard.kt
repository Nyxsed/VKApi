package ru.simakover.vkapi.presentation.screens.newsfeed

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.simakover.vkapi.R
import ru.simakover.vkapi.domain.models.FeedPost
import ru.simakover.vkapi.domain.models.StatisticItem
import ru.simakover.vkapi.domain.models.StatisticType
import ru.simakover.vkapi.presentation.ui.theme.likedHeart
import ru.simakover.vkapi.presentation.util.Util.mapTimestampToDate

@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    post: FeedPost,
    onLikeClickListener: (StatisticItem) -> Unit,
    onCommentClickListener: (StatisticItem) -> Unit,
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            PostHeader(post = post)
            Text(text = post.contentText)
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                placeholder = painterResource(id = R.drawable.ic_placeholder),
                model = post.contentImageUrl,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
            )
            Statistics(
                statistics = post.statistics,
                onLikeClickListener = onLikeClickListener,
                onCommentClickListener = onCommentClickListener,
                isLiked = post.isLiked
            )
        }
    }
}

@Composable
private fun PostHeader(
    post: FeedPost,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically

    ) {
        AsyncImage(
            modifier = Modifier
                .clip(CircleShape)
                .size(50.dp),
            model = post.communityImageUrl,
            contentDescription = null,
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
                text = post.communityName,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = mapTimestampToDate(post.publicationDate),
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

@Composable
private fun Statistics(
    statistics: List<StatisticItem>,
    onLikeClickListener: (StatisticItem) -> Unit,
    onCommentClickListener: (StatisticItem) -> Unit,
    isLiked: Boolean,
) {
    Row(
        modifier = Modifier
            .padding(top = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
        ) {
            val viewsItem = statistics.getItemByType(StatisticType.VIEWS)
            IconWithText(
                iconResId = R.drawable.ic_eye,
                text = viewsItem.count.toString(),
                revers = false
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val sharesItem = statistics.getItemByType(StatisticType.SHARES)
            IconWithText(
                iconResId = R.drawable.ic_arrow,
                text = sharesItem.count.toString(),
            )
            Spacer(modifier = Modifier.width(12.dp))
            val commentItem = statistics.getItemByType(StatisticType.COMMENTS)
            IconWithText(
                iconResId = R.drawable.ic_comment,
                text = commentItem.count.toString(),
                onItemClickListener = {
                    onCommentClickListener(commentItem)
                },
            )
            Spacer(modifier = Modifier.width(12.dp))
            val likeItem = statistics.getItemByType(StatisticType.LIKES)
            IconWithText(
                iconResId = if (isLiked) R.drawable.ic_like_set else R.drawable.ic_like,
                text = likeItem.count.toString(),
                onItemClickListener = {
                    onLikeClickListener(likeItem)
                },
                tintForIcon = if (isLiked) likedHeart else MaterialTheme.colorScheme.onSecondary
            )
        }
    }
}

// return stat item matching type
private fun List<StatisticItem>.getItemByType(type: StatisticType): StatisticItem {
    return this.find { item ->
        item.type == type
    } ?: throw IllegalStateException()
}

@Composable
private fun IconWithText(
    iconResId: Int,
    text: String,
    onItemClickListener: (() -> Unit)? = null,
    revers: Boolean = true,
    tintForIcon: Color = MaterialTheme.colorScheme.onSecondary,
) {

    val modifier = if (onItemClickListener == null) {
        Modifier
    } else {
        Modifier.clickable { onItemClickListener() }
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (revers) {
            Text(
                modifier = Modifier
                    .padding(end = 4.dp),
                text = formatStatisticCount(text),
                color = MaterialTheme.colorScheme.onSecondary
            )
            Icon(
                modifier = Modifier
                    .size(20.dp),
                painter = painterResource(id = iconResId),
                contentDescription = null,
                tint = tintForIcon,
            )
        } else {
            Icon(
                modifier = Modifier
                    .size(20.dp)
                    .padding(end = 4.dp),
                painter = painterResource(id = iconResId),
                contentDescription = null,
                tint = tintForIcon,
            )
            Text(
                text = formatStatisticCount(text),
                color = MaterialTheme.colorScheme.onSecondary
            )
        }
    }
}

private fun formatStatisticCount(count: String): String {
    val longCount = count.toLong()
    return if (longCount > 100_000) {
        String.format("%sK", (longCount / 1000))
    } else if (longCount > 1000) {
        String.format("%.1fK", (longCount / 1000f))
    } else {
        longCount.toString()
    }
}