package com.example.jetpackcomposecourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.jetpackcomposecourse.ui.screens.home.NewsFeedViewModel
import com.example.jetpackcomposecourse.ui.theme.JetpackComposeCourseTheme
import com.example.jetpackcomposecourse.ui.theme.MainScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeCourseTheme {
                MainScreen()
            }
        }
    }
}