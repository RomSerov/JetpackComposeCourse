package com.example.jetpackcomposecourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import com.example.jetpackcomposecourse.ui.screens.main.MainScreen
import com.example.jetpackcomposecourse.ui.theme.JetpackComposeCourseTheme
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAuthenticationResult
import com.vk.api.sdk.auth.VKScope

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeCourseTheme {

                // авторизация VK, подписка на результат
                val launcher = rememberLauncherForActivityResult(
                    contract = VK.getVKAuthActivityResultContract(),
                    onResult = {
                        when (it) {
                            is VKAuthenticationResult.Failed -> TODO()
                            is VKAuthenticationResult.Success -> TODO()
                        }
                    }
                )

                launcher.launch(listOf(VKScope.WALL))

                MainScreen()
            }
        }
    }
}