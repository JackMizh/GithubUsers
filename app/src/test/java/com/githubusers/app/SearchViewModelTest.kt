@file:Suppress("DEPRECATION")

package com.githubusers.app

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.githubusers.app.data.UserListItem
import com.githubusers.app.data.UserListResponse
import com.githubusers.app.repository.SearchRepository
import com.githubusers.app.viewmodels.SearchViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var searchRepository: SearchRepository

    @Mock
    private lateinit var observer: Observer<List<UserListItem>>

    private lateinit var searchViewModel: SearchViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        searchViewModel = SearchViewModel(searchRepository)
        searchViewModel.getSearchUserLiveData.observeForever(observer)
    }

    @Test
    fun `test searchUser success`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val keyword = "test"
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
        Mockito.`when`(searchRepository.searchUser(keyword, 1)).thenReturn(Response.success(response))

        searchViewModel.searchUser(keyword)

        Mockito.verify(observer).onChanged(userListItems)
    }

    @Test
    fun `test searchUserNextPage success`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val keyword = "test"
        val userListItems = List(30) {
            UserListItem(
                login = "testUser$it",
                id = it,
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
        }
        val response = UserListResponse(
            items = userListItems,
            total_count = 1,
            incomplete_results = false
        )
        Mockito.`when`(searchRepository.searchUser(keyword, 1)).thenReturn(Response.success(response))

        searchViewModel.searchUser(keyword)
        Mockito.verify(observer).onChanged(userListItems)

        Mockito.`when`(searchRepository.searchUser(keyword, 2)).thenReturn(Response.success(response))
        searchViewModel.searchUserNextPage(keyword)

        Mockito.verify(observer).onChanged(userListItems + userListItems)
    }

    @Test
    fun `test deleteLocalData success`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        searchViewModel.deleteLocalData()
        Mockito.verify(searchRepository).deleteLocalData()
    }
}