package com.githubusers.app.repository

import com.githubusers.app.api.GithubApi
import com.githubusers.app.data.UserResponse
import com.githubusers.app.db.UsersDao
import retrofit2.Response
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val githubApi: GithubApi,
    private val usersDao: UsersDao
) {
    suspend fun getUser(login: String): Response<UserResponse> {
        return githubApi.getUser(login)
    }

    suspend fun insertLocalData(userResponse: UserResponse) = usersDao.insertLocalData(userResponse)

    suspend fun getLocalData(id: Int) = usersDao.getLocalData(id)
}