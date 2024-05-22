package com.githubusers.app.api

import com.githubusers.app.data.UserListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    @GET("search/users")
    suspend fun searchUser(
        @Query("q") keyword: String,
    ): Response<UserListResponse>
}