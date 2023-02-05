package com.example.jetpackcomposecourse.presentation.comments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackcomposecourse.domain.FeedPost
import com.example.jetpackcomposecourse.domain.PostComment

class CommentsViewModel(feedPost: FeedPost) : ViewModel()  {

    private val _screenState = MutableLiveData<CommentsScreenState>(CommentsScreenState.Initial)
    val screenState: LiveData<CommentsScreenState> = _screenState

    init {
        loadComments(feedPost)
    }

    fun loadComments(feedPost: FeedPost) {
        val fakeComments = mutableListOf<PostComment>().apply {
            repeat(10) {
                add(PostComment(id = it))
            }
        }
        _screenState.value = CommentsScreenState.Comments(
            comments = fakeComments,
            feedPost = feedPost
        )
    }
}