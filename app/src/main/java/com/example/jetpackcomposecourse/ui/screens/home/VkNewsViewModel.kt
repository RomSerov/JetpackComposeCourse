package com.example.jetpackcomposecourse.ui.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackcomposecourse.domain.FeedPost
import com.example.jetpackcomposecourse.domain.PostComment
import com.example.jetpackcomposecourse.domain.StatisticItem
import com.example.jetpackcomposecourse.ui.screens.main.NavigationItem

class VkNewsViewModel: ViewModel() {

    private val fakeComments = mutableListOf<PostComment>().apply {
        repeat(10) {
            add(PostComment(id = it))
        }
    }

    private val sourceList = mutableListOf<FeedPost>().apply {
        repeat(10) {
            add(FeedPost(id = it))
        }
    }

    private val initialState = HomeScreenState.Posts(posts = sourceList)

    private val _screenState = MutableLiveData<HomeScreenState>(initialState)
    val screenState: LiveData<HomeScreenState> = _screenState

    private val _selectedNavItem = MutableLiveData<NavigationItem>(NavigationItem.Home)
    val selectedNavItem: LiveData<NavigationItem> = _selectedNavItem

    /* Сохранить состояние */
    private var saveState : HomeScreenState? = initialState

    fun selectNavItem(item: NavigationItem) {
        _selectedNavItem.value = item
    }

    fun showComments(feedPost: FeedPost) {
        saveState = _screenState.value
        _screenState.value = HomeScreenState.Comments(comments = fakeComments, feedPost = feedPost)
    }

    fun closeComment() {
        _screenState.value = saveState
    }

    fun updateCount(feedPost: FeedPost, item: StatisticItem) {

        val currentState = screenState.value
        if (currentState !is HomeScreenState.Posts) return

        val oldPosts = currentState.posts.toMutableList()
        val oldStatistics = feedPost.statistics
        val newStatistics = oldStatistics.toMutableList().apply {
            replaceAll { oldItem ->
                if (oldItem.type == item.type) {
                    oldItem.copy(count = oldItem.count + 1)
                } else {
                    oldItem
                }
            }
        }
        val newFeedPost = feedPost.copy(statistics = newStatistics)
        val newPost = oldPosts.apply {
            replaceAll {
                if (it.id == newFeedPost.id) {
                    newFeedPost
                } else {
                    it
                }
            }
        }
        _screenState.value = HomeScreenState.Posts(posts = newPost)
    }

    fun remove(feedPost: FeedPost) {
        val currentState = screenState.value
        if (currentState !is HomeScreenState.Posts) return

        val oldPosts = currentState.posts.toMutableList()
        oldPosts.remove(feedPost)
        _screenState.value = HomeScreenState.Posts(posts = oldPosts)
    }
}