package com.githubusers.app

import com.githubusers.app.api.GithubApi
import com.githubusers.app.data.UserListItem
import com.githubusers.app.data.UserListResponse
import com.githubusers.app.db.UsersDao
import com.githubusers.app.repository.SearchRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class SearchRepositoryTest {
    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    private lateinit var githubApi: GithubApi

    @Mock
    private lateinit var usersDao: UsersDao

    private lateinit var searchRepository: SearchRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        searchRepository = SearchRepository(githubApi, usersDao)
    }

    @Test
    fun `test searchUser success`() = runBlocking {
        val keyword = "test"
        val page = 1
        val userListItems = listOf(
            UserListItem(
                login = "mojombo",
                id =  1,
                node_id = "MDQ6VXNlcjE=",
                avatar_url = "https://secure.gravatar.com/avatar/25c7c18223fb42a4c6ae1c8db6f50f9b?d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-user-420.png",
                gravatar_id = "",
                url = "https://api.github.com/users/mojombo",
                html_url = "https://github.com/mojombo",
                followers_url = "https://api.github.com/users/mojombo/followers",
                subscriptions_url = "https://api.github.com/users/mojombo/subscriptions",
                organizations_url = "https://api.github.com/users/mojombo/orgs",
                repos_url = "https://api.github.com/users/mojombo/repos",
                received_events_url = "https://api.github.com/users/mojombo/received_events",
                type = "User",
                score = 1.0,
                following_url = "https://api.github.com/users/mojombo/following{/other_user}",
                gists_url = "https://api.github.com/users/mojombo/gists{/gist_id}",
                starred_url = "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
                events_url = "https://api.github.com/users/mojombo/events{/privacy}",
                site_admin = true
            )
        )
        val response = UserListResponse(
            items = userListItems,
            total_count = 1,
            incomplete_results = false
        )
        Mockito.`when`(githubApi.searchUser(keyword, page)).thenReturn(Response.success(response))

        val result = searchRepository.searchUser(keyword, page)
        assert(result.isSuccessful)
        assert(result.body() == response)
    }

    @Test
    fun `test deleteLocalData success`() = runBlocking {
        searchRepository.deleteLocalData()
        Mockito.verify(usersDao).deleteLocalData()
    }
}