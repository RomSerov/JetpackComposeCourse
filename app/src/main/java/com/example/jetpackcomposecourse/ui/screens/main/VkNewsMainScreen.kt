package com.example.jetpackcomposecourse.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.jetpackcomposecourse.ui.screens.home.VkNewsViewModel
import com.example.jetpackcomposecourse.ui.navigation.AppNavGraph
import com.example.jetpackcomposecourse.ui.navigation.rememberNavigationState
import com.example.jetpackcomposecourse.ui.screens.HomeScreen
import com.example.jetpackcomposecourse.ui.screens.main.NavigationItem

@Composable
fun MainScreen(viewModel: VkNewsViewModel) {

    val navigationState = rememberNavigationState()

    Scaffold(
        bottomBar = {
            BottomNavigation {
                //стэйт для текущего открытого экрана
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favourite,
                    NavigationItem.Profile
                )
                items.forEach { item ->
                    BottomNavigationItem(
                        selected = currentRoute == item.screen.route,
                        onClick = { navigationState.navigate(item.screen.route) },
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
            homeScreenContent = {
                HomeScreen(viewModel = viewModel, paddingValues = paddingValues)
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