package com.example.jetpackcomposecourse.navigation

import android.net.Uri
import com.example.jetpackcomposecourse.domain.FeedPost
import com.google.gson.Gson

sealed class Screen(val route: String) {

    object NewsFeed: Screen(route = ROUTE_NEWS_FEED)
    object Favorite: Screen(route = ROUTE_FAVORITE)
    object Profile: Screen(route = ROUTE_PROFILE)

    object Home: Screen(route = ROUTE_HOME)

    object Comments: Screen(route = ROUTE_COMMENTS) {
        private const val ROUTE_FOR_ARGS = "comments"

        fun getRouteWithArgs(feedPost: FeedPost): String {
            /* передача целиком объекта */
            val feedPostJson = Gson().toJson(feedPost)
            return "$ROUTE_FOR_ARGS/${feedPostJson.encode()}"
        }
    }

    companion object {

        /* parameters navigation */
        const val KEY_FEED_POST = "feed_post"

        /* main graph */
        const val ROUTE_NEWS_FEED = "news_feed"
        const val ROUTE_FAVORITE = "favorite"
        const val ROUTE_PROFILE = "profile"

        /* nested home graph */
        const val ROUTE_HOME = "home"
        const val ROUTE_COMMENTS = "comments/{$KEY_FEED_POST}"
    }
}

/* расширение для использования спец символов таких как "/" и тд
* использование "/" может привести к неправильной передаче аргументов */
fun String.encode(): String {
    return Uri.encode(this)
}