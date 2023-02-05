package com.example.jetpackcomposecourse.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpackcomposecourse.ui.theme.JetpackComposeCourseTheme
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeCourseTheme {
                val viewModel: MainViewModel = viewModel()

                val authState = viewModel.authState.observeAsState(AuthState.Initial)
                /* авторизация VK, подписка на результат */
                val launcher = rememberLauncherForActivityResult(
                    contract = VK.getVKAuthActivityResultContract(),
                    onResult = {
                        viewModel.performAuthResult(result = it)
                    })

                when(authState.value) {
                    AuthState.Authorized -> {
                        MainScreen()
                    }
                    AuthState.Initial -> Unit
                    AuthState.NotAuthorized -> {
                        LoginScreen {
                            launcher.launch(listOf(VKScope.WALL, VKScope.FRIENDS))
                        }
                    }
                }
            }
        }
    }
}