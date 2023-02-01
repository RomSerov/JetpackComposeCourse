package com.example.jetpackcomposecourse.domain

import com.example.jetpackcomposecourse.R

data class PostComment(
    val id: Int,
    val authorName: String = "Author",
    val authorAvatarId: Int = R.drawable.comment_author_avatar,
    val commentText: String = "Comment text",
    val publicationDate: String = "14:00"
)
