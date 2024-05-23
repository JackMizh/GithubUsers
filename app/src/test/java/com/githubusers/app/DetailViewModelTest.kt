@file:Suppress("DEPRECATION")

package com.githubusers.app

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.githubusers.app.data.UserResponse
import com.githubusers.app.repository.DetailRepository
import com.githubusers.app.viewmodels.DetailViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var detailRepository: DetailRepository

    @Mock
    private lateinit var detailViewModel: DetailViewModel

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        detailViewModel = DetailViewModel(detailRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `getUser should fetch and post local data if available`() = runBlockingTest {
        val id = 1
        val login = "testUser"
        val localUser = UserResponse(
            "url",
            "bio",
            "email",
            100,
            50,
            id,
            "location",
            login,
            "name",
            10
        )

        `when`(detailRepository.getLocalData(id)).thenReturn(localUser)

        detailViewModel.getUser(id, login)

        val result = LiveDataTestUtil.getValue(detailViewModel.getUserLiveData)
        assertEquals(result, localUser)
        verify(detailRepository).getLocalData(id)
        verify(detailRepository, never()).getUser(login)
    }

    @Test
    fun `getUser should fetch from remote if local data is null`() = runBlockingTest {
        val id = 1
        val login = "testUser"
        val remoteUser = UserResponse(
            "url",
            "bio",
            "email",
            100,
            50,
            id,
            "location",
            login,
            "name",
            10
        )
        val response = Response.success(remoteUser)

        `when`(detailRepository.getLocalData(id)).thenReturn(null)
        `when`(detailRepository.getUser(login)).thenReturn(response)

        detailViewModel.getUser(id, login)

        val result = LiveDataTestUtil.getValue(detailViewModel.getUserLiveData)
        assertEquals(result, remoteUser)
        verify(detailRepository).getLocalData(id)
        verify(detailRepository).getUser(login)
        verify(detailRepository).insertLocalData(remoteUser)
    }
}