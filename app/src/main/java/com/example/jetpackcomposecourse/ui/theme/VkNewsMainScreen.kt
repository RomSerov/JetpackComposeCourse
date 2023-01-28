package com.example.jetpackcomposecourse.ui.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposecourse.R
import com.example.jetpackcomposecourse.domain.FeedPost
import com.example.jetpackcomposecourse.ui.VkNewsViewModel

@Composable
fun MainScreen(viewModel: VkNewsViewModel) {

    Scaffold(

        bottomBar = {
            BottomNavigation() {

                val selectedItemPosition = remember {
                    mutableStateOf(0)
                }

                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favorite,
                    NavigationItem.Profile
                )

                items.forEachIndexed { index, item ->
                    BottomNavigationItem(
                        selected = selectedItemPosition.value == index,
                        onClick = { selectedItemPosition.value = index },
                        icon = {
                            Icon(item.icon, contentDescription = null)
                        },
                        label = {
                            Text(text = stringResource(id = item.titleResId))
                        },
                        selectedContentColor = MaterialTheme.colors.onPrimary,
                        unselectedContentColor = MaterialTheme.colors.onSecondary
                    )
                }
            }
        }

    )
    {
        val feedPost = viewModel.feedPost.observeAsState(FeedPost())
        PostCard(
            modifier = Modifier.padding(8.dp),
            feedPost = feedPost.value,
            onViewsItemClickListener = viewModel::updateCount,
            onShareItemClickListener = viewModel::updateCount,
            onCommentItemClickListener = viewModel::updateCount,
            onLikeItemClickListener = viewModel::updateCount
        )
    }
}

sealed class NavigationItem(val titleResId: Int, val icon: ImageVector) {
    object Home :
        NavigationItem(titleResId = R.string.navigation_item_home, icon = Icons.Outlined.Home)

    object Favorite : NavigationItem(
        titleResId = R.string.navigation_item_favorite,
        icon = Icons.Outlined.Favorite
    )

    object Profile :
        NavigationItem(titleResId = R.string.navigation_item_profile, icon = Icons.Outlined.Person)
}