package com.example.jetpackcomposecourse.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposecourse.R


@Composable
fun InstagramProfileCard(
    model: InstagramModel,
    onFollowedBtnClickListener: (InstagramModel) -> Unit
) {
    Card(
        modifier = Modifier.padding(8.dp),
        backgroundColor = MaterialTheme.colors.background,
        shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.onBackground)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Image(
                    painterResource(id = R.drawable.ic_inst),
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(shape = CircleShape)
                        .background(Color.White)
                        .padding(8.dp)
                )
                UserStatistic(title = "Posts", value = "6,950")
                UserStatistic(title = "Followers", value = "436")
                UserStatistic(title = "Following", value = "59")
            }
            Text(text = "Instagram ${model.id}", fontSize = 32.sp, fontFamily = FontFamily.Cursive)
            Text(text = "#${model.title}", fontSize = 14.sp)
            Text(text = "www.facebook.com/dagestan", fontSize = 14.sp)
            FollowButton(isFollowedBtn = model.isFollowed) {
                onFollowedBtnClickListener(model)
            }
        }
    }
}

@Composable
private fun FollowButton(
    isFollowedBtn: Boolean,
    clickFollow: () -> Unit
) {
    Button(
        onClick = { clickFollow() },
        colors = ButtonDefaults.buttonColors(
            if (isFollowedBtn) {
                MaterialTheme.colors.primary.copy(alpha = 0.5f)
            } else {
                MaterialTheme.colors.primary
            }
        )
    ) {
        val textBtn = if (isFollowedBtn) {
            "Отписаться"
        } else {
            "Подписаться"
        }
        Text(text = textBtn)
    }
}

@Composable
private fun UserStatistic(
    title: String,
    value: String
) {
    Column(
        modifier = Modifier.height(80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    )
    {
        Text(
            text = value,
            fontSize = 24.sp,
            fontFamily = FontFamily.Cursive
        )
        Text(
            text = title,
            fontWeight = FontWeight.Bold
        )
    }
}