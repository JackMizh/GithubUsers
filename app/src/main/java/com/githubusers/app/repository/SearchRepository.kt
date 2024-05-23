package com.githubusers.app.repository

import com.githubusers.app.api.GithubApi
import com.githubusers.app.data.UserListResponse
import com.githubusers.app.db.UsersDao
import retrofit2.Response
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val githubApi: GithubApi,
    private val usersDao: UsersDao
) {
    suspend fun searchUser(keyword: String): Response<UserListResponse> {
        return githubApi.searchUser(keyword)
    }

    suspend fun deleteLocalData() = usersDao.deleteLocalData()
}