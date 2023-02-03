package com.example.jetpackcomposecourse.ui.theme

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*

@Composable
fun ActivityResultTest() {

    var imageUri by remember {
        mutableStateOf(Uri.EMPTY)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            imageUri = it
        }
    )
}