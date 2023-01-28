package com.example.jetpackcomposecourse.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecourse.R
import com.example.jetpackcomposecourse.domain.FeedPost
import com.example.jetpackcomposecourse.domain.StatisticItem
import com.example.jetpackcomposecourse.domain.StatisticType

@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    feedPost: FeedPost,
    onViewsItemClickListener: (StatisticItem) -> Unit,
    onShareItemClickListener: (StatisticItem) -> Unit,
    onCommentItemClickListener: (StatisticItem) -> Unit,
    onLikeItemClickListener: (StatisticItem) -> Unit
) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(8.dp)) {

            PostHeader(feedPost)

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = feedPost.contentText)

            Spacer(modifier = Modifier.height(8.dp))

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                painter = painterResource(id = feedPost.contentImageResId),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )

            Spacer(modifier = Modifier.height(8.dp))

            StatisticBar(
                statistics = feedPost.statistics,
                onViewsItemClickListener = onViewsItemClickListener,
                onShareItemClickListener = onShareItemClickListener,
                onCommentItemClickListener = onCommentItemClickListener,
                onLikeItemClickListener = onLikeItemClickListener
            )
        }
    }
}

@Composable
private fun PostHeader(feedPost: FeedPost) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            painter = painterResource(id = feedPost.avatarResId),
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = feedPost.groupName, color = MaterialTheme.colors.onPrimary)
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = feedPost.postDate, color = MaterialTheme.colors.onSecondary)
        }

        Icon(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = null,
            tint = MaterialTheme.colors.onSecondary
        )
    }
}

@Composable
private fun StatisticBar(
    statistics: List<StatisticItem>,
    onViewsItemClickListener: (StatisticItem) -> Unit,
    onShareItemClickListener: (StatisticItem) -> Unit,
    onCommentItemClickListener: (StatisticItem) -> Unit,
    onLikeItemClickListener: (StatisticItem) -> Unit
) {
    Row() {
        Row(modifier = Modifier.weight(1f))
        {
            val viewsItem = statistics.getItemByType(type = StatisticType.VIEWS)
            IconWithText(
                iconResId = R.drawable.ic_views_count,
                text = viewsItem.count.toString(),
                onItemClickListener = {
                    onViewsItemClickListener(viewsItem)
                }
            )
        }
        Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.SpaceBetween)
        {
            val sharesItem = statistics.getItemByType(type = StatisticType.SHARES)
            IconWithText(
                iconResId = R.drawable.ic_share,
                text = sharesItem.count.toString(),
                onItemClickListener = {
                    onShareItemClickListener(sharesItem)
                }
            )
            val commentsItem = statistics.getItemByType(type = StatisticType.COMMENTS)
            IconWithText(
                iconResId = R.drawable.ic_comment,
                text = commentsItem.count.toString(),
                onItemClickListener = {
                    onCommentItemClickListener(commentsItem)
                }
            )
            val likeItem = statistics.getItemByType(type = StatisticType.LIKES)
            IconWithText(
                iconResId = R.drawable.ic_like,
                text = likeItem.count.toString(),
                onItemClickListener = {
                    onLikeItemClickListener(likeItem)
                }
            )
        }
    }
}

private fun List<StatisticItem>.getItemByType(type: StatisticType): StatisticItem {
    return this.find {
        it.type == type
    } ?: throw IllegalStateException()
}

@Composable
private fun IconWithText(
    iconResId: Int,
    text: String,
    onItemClickListener: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            onItemClickListener()
        }
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = MaterialTheme.colors.onSecondary
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(text = text, color = MaterialTheme.colors.onSecondary)
    }
}