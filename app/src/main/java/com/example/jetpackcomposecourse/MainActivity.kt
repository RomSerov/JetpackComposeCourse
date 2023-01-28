package com.example.jetpackcomposecourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.jetpackcomposecourse.ui.VkNewsViewModel
import com.example.jetpackcomposecourse.ui.theme.JetpackComposeCourseTheme
import com.example.jetpackcomposecourse.ui.theme.MainScreen

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<VkNewsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeCourseTheme {
                MainScreen(viewModel)
            }
        }
    }
}