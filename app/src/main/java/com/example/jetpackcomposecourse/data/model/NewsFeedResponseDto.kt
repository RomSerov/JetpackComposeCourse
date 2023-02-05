package com.example.jetpackcomposecourse.data.model

import com.google.gson.annotations.SerializedName

data class NewsFeedResponseDto(
    @SerializedName("response") val newsFeed: NewsFeedDto
)
