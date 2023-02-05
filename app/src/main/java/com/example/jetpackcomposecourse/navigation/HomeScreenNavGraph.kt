package com.example.jetpackcomposecourse.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.jetpackcomposecourse.domain.FeedPost
import com.example.jetpackcomposecourse.navigation.Screen.Companion.KEY_FEED_POST
import com.google.gson.Gson

fun NavGraphBuilder.homeScreenNavGraph(
    newsFeedScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable (FeedPost) -> Unit
) {
    navigation(
        startDestination = Screen.NewsFeed.route,
        route = Screen.Home.route
    ) {
        composable(route = Screen.NewsFeed.route) {
            newsFeedScreenContent()
        }

        composable(
            route = Screen.Comments.route,
            arguments = listOf(
                navArgument(name = KEY_FEED_POST) {
                    type = NavType.StringType
                }
            )
            ) {
            /* получаем id для коментов из NavBackStackEntry */
            val feedPostJson = it.arguments?.getString(KEY_FEED_POST) ?: ""
            val feedPost = Gson().fromJson<FeedPost>(feedPostJson, FeedPost::class.java)

            commentsScreenContent(feedPost)
        }
    }
}