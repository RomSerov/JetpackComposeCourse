package com.example.jetpackcomposecourse.ui.screens.home

import com.example.jetpackcomposecourse.domain.FeedPost

sealed class  NewsFeedScreenState {

    object Initial: NewsFeedScreenState()
    data class Posts(val posts: List<FeedPost>): NewsFeedScreenState()
}
