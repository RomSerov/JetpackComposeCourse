package com.example.jetpackcomposecourse.ui.screens.comment

import com.example.jetpackcomposecourse.domain.FeedPost
import com.example.jetpackcomposecourse.domain.PostComment

sealed class  CommentsScreenState {

    object Initial: CommentsScreenState()
    data class Comments(val comments: List<PostComment>, val feedPost: FeedPost): CommentsScreenState()
}
