@file:OptIn(ExperimentalCoroutinesApi::class)
@file:Suppress("DEPRECATION")

package com.githubusers.app

import com.githubusers.app.api.GithubApi
import com.githubusers.app.data.UserResponse
import com.githubusers.app.db.UsersDao
import com.githubusers.app.repository.DetailRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class DetailRepositoryTest {

    @Mock
    private lateinit var githubApi: GithubApi

    @Mock
    private lateinit var usersDao: UsersDao

    private lateinit var detailRepository: DetailRepository

    @Before
    fun setUp() {
        detailRepository = DetailRepository(githubApi, usersDao)
    }

    @Test
    fun `getUser should fetch from remote when local data is null`() = runBlockingTest {
        val login = "testUser"
        val remoteUser = UserResponse(
            "url",
            "bio",
            "email",
            100,
            50,
            1,
            "location",
            login,
            "name",
            10
        )
        val response = Response.success(remoteUser)

        `when`(githubApi.getUser(login)).thenReturn(response)

        val result = detailRepository.getUser(login)

        verify(githubApi).getUser(login)
        assertEquals(result, response)
    }

    @Test
    fun `insertLocalData should save data locally`() = runBlockingTest {
        val user = UserResponse(
            "url",
            "bio",
            "email",
            100,
            50,
            1,
            "location",
            "testUser",
            "name",
            10
        )

        detailRepository.insertLocalData(user)

        verify(usersDao).insertLocalData(user)
    }

    @Test
    fun `getLocalData should fetch from local database`() = runBlockingTest {
        val id = 1
        val localUser = UserResponse(
            "url",
            "bio",
            "email",
            100,
            50,
            1,
            "location",
            "testUser",
            "name",
            10
        )

        `when`(usersDao.getLocalData(id)).thenReturn(localUser)

        val result = detailRepository.getLocalData(id)

        verify(usersDao).getLocalData(id)
        assertEquals(result, localUser)
    }
}