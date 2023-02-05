package com.example.jetpackcomposecourse.presentation.news

import com.example.jetpackcomposecourse.domain.FeedPost

sealed class  NewsFeedScreenState {

    object Initial: NewsFeedScreenState()
    data class Posts(val posts: List<FeedPost>): NewsFeedScreenState()
}
