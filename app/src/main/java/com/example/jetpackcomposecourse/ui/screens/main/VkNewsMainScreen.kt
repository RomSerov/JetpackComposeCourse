package com.example.jetpackcomposecourse.ui.screens.main

import androidx.compose.foundation.clickable
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.jetpackcomposecourse.domain.FeedPost
import com.example.jetpackcomposecourse.ui.navigation.AppNavGraph
import com.example.jetpackcomposecourse.ui.navigation.Screen
import com.example.jetpackcomposecourse.ui.navigation.rememberNavigationState
import com.example.jetpackcomposecourse.ui.screens.CommentsScreen
import com.example.jetpackcomposecourse.ui.screens.HomeScreen

@Composable
fun MainScreen() {

    val navigationState = rememberNavigationState()

    Scaffold(
        bottomBar = {
            BottomNavigation {
                //стэйт для текущего открытого экрана
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()

                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favourite,
                    NavigationItem.Profile
                )
                items.forEach { item ->

                    /* Если находимся на экране коментов или постов, для любого (any) вернуть true */
                    val selected = navBackStackEntry?.destination?.hierarchy?.any {
                        it.route == item.screen.route
                    } ?: false

                    BottomNavigationItem(
                        selected = selected,
                        /* при клике на нижний таб нужно игнорировать еще раз вызов */
                        onClick = { if (!selected) {
                            navigationState.navigate(item.screen.route)
                        } },
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
    ) { paddingValues ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            newsFeedScreenContent = {
                    HomeScreen(
                        paddingValues = paddingValues,
                        onCommentClickListener = {
                            navigationState.navigateToComments(it)
                        }
                    )

            },
            commentsScreenContent = { feedPost ->
                CommentsScreen(
                    feedPost = feedPost,
                    onBackPressed = { navigationState.navHostController.popBackStack() })
            },
            favoriteScreenContent = {
                TextCounter(name = "Favourite")
            },
            profileScreenContent = {
                TextCounter(name = "Profile")
            }
        )
    }
}

@Composable
private fun TextCounter(name: String) {
    var count by rememberSaveable {
        mutableStateOf(0)
    }

    Text(
        modifier = Modifier.clickable { count++ },
        text = "$name Count: $count",
        color = Color.Black
    )
}