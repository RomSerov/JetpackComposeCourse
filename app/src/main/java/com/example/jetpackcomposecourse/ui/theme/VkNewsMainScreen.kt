package com.example.jetpackcomposecourse.ui.theme

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.jetpackcomposecourse.R
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {

    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val fabVisible = remember { mutableStateOf(true) }

    Scaffold(

        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },

        floatingActionButton = {
            if (fabVisible.value) {
                FloatingActionButton(onClick = {
                    scope.launch {
                        val action = snackBarHostState.showSnackbar(message = "Show snackBar", actionLabel = "Скрыть FAB", duration = SnackbarDuration.Long)
                        if (action == SnackbarResult.ActionPerformed) {
                            fabVisible.value = false
                        }
                    }
                })
                {
                    Icon(Icons.Filled.Favorite, contentDescription = null)
                }
            }
        },

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