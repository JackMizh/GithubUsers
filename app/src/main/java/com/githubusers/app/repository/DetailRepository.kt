package com.githubusers.app.repository

import com.githubusers.app.api.GithubApi
import com.githubusers.app.data.UserResponse
import retrofit2.Response
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val githubApi: GithubApi
) {
    suspend fun getUser(login: String): Response<UserResponse> {
        return githubApi.getUser(login)
    }
}