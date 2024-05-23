package com.githubusers.app.api

import com.githubusers.app.data.UserListResponse
import com.githubusers.app.data.UserResponse
import com.githubusers.app.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("search/users")
    suspend fun searchUser(
        @Query("q") keyword: String,
        @Header("Authorization") accessToken: String = Constants.personalAccessToken
    ): Response<UserListResponse>

    @GET("users/{username}")
    fun getUser(@Path("username") username: String
    ): Response<UserResponse>
}