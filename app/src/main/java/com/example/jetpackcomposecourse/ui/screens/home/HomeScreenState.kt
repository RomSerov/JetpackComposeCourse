package com.example.jetpackcomposecourse.ui.screens.home

import com.example.jetpackcomposecourse.domain.FeedPost
import com.example.jetpackcomposecourse.domain.PostComment

sealed class HomeScreenState {

    object Initial: HomeScreenState()
    data class Posts(val posts: List<FeedPost>): HomeScreenState()
    data class Comments(val feedPost: FeedPost, val comments: List<PostComment>): HomeScreenState()
}
