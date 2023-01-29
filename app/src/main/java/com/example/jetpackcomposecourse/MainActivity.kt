package com.example.jetpackcomposecourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackcomposecourse.ui.InstagramProfileCard
import com.example.jetpackcomposecourse.ui.InstagramViewModel
import com.example.jetpackcomposecourse.ui.theme.JetpackComposeCourseTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this)[InstagramViewModel::class.java]

        setContent {
            JetpackComposeCourseTheme {
                val models = viewModel.models.observeAsState(listOf())
                LazyColumn {
                    items(models.value, key = { it.id }) { model ->
                        val dismissState = rememberDismissState()
                        if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                            viewModel.delete(model = model)
                        }
                        SwipeToDismiss(
                            state = dismissState,
                            directions = setOf(DismissDirection.EndToStart),
                            background = {
                                Box(
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .background(Color.Red.copy(alpha = 0.5f))
                                        .fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(modifier = Modifier.padding(16.dp),text = "Удалить", color = Color.White, fontSize = 24.sp)
                                }
                            }) {
                            InstagramProfileCard(
                                model = model,
                                onFollowedBtnClickListener = {
                                    viewModel.changeFollowingStatus(it)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}