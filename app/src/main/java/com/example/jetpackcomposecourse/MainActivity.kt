package com.example.jetpackcomposecourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackcomposecourse.ui.InstagramProfileCard
import com.example.jetpackcomposecourse.ui.InstagramViewModel
import com.example.jetpackcomposecourse.ui.theme.JetpackComposeCourseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this)[InstagramViewModel::class.java]

        setContent {
            JetpackComposeCourseTheme {
                InstagramProfileCard(viewModel = viewModel)
            }
        }
    }
}