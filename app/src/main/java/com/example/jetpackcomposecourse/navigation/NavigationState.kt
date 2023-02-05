package com.example.jetpackcomposecourse.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposecourse.domain.FeedPost

class NavigationState(val navHostController: NavHostController) {

    fun navigate(route: String) {
        navHostController.navigate(route = route) {
            /* стартовым направлением считается вложенный граф
            * все экраны до главного будут выкидываться из backstack */

            popUpTo(navHostController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    /* исключение для коментов, чтоб не выкидывать из backstack */
    fun navigateToComments(feedPost: FeedPost) {
        /* передать параметр id для коментов */
        navHostController.navigate(Screen.Comments.getRouteWithArgs(feedPost = feedPost))
    }
}

@Composable
fun rememberNavigationState(navHostController: NavHostController = rememberNavController()): NavigationState {
    return remember {
        NavigationState(navHostController = navHostController)
    }
}