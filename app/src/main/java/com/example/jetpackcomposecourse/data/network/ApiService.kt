package com.example.jetpackcomposecourse.data.network

import com.example.jetpackcomposecourse.data.model.NewsFeedResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("newsfeed.getRecommended?v=5.131")
    suspend fun loadRecommendation(@Query("access_token") token: String): NewsFeedResponseDto
}