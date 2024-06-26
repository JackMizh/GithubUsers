package com.githubusers.app.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.githubusers.app.data.UserResponse
import com.githubusers.app.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailRepository: DetailRepository,
): ViewModel(){

    private val _userLiveData = MutableLiveData<UserResponse>()
    val getUserLiveData: LiveData<UserResponse> = _userLiveData

    fun getUser(id: Int, login: String){
        viewModelScope.launch {
            val localData = detailRepository.getLocalData(id)

            if(localData != null){
                _userLiveData.postValue(localData)
            } else {
                val response = detailRepository.getUser(login)
                _userLiveData.postValue(response.body())
                detailRepository.insertLocalData(response.body()!!)
            }
        }
    }
}