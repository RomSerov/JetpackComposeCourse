package com.example.jetpackcomposecourse.ui.screens.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.jetpackcomposecourse.R
import com.example.jetpackcomposecourse.ui.navigation.Screen

sealed class NavigationItem(
    val screen: Screen,
    val titleResId: Int,
    val icon: ImageVector
) {

    object Home : NavigationItem(
        screen = Screen.NewsFeed,
        titleResId = R.string.navigation_item_home,
        icon = Icons.Outlined.Home
    )

    object Favourite : NavigationItem(
        screen = Screen.Favorite,
        titleResId = R.string.navigation_item_favorite,
        icon = Icons.Outlined.Favorite
    )

    object Profile : NavigationItem(
        screen = Screen.Profile,
        titleResId = R.string.navigation_item_profile,
        icon = Icons.Outlined.Person
    )
}
