package com.githubusers.app.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.githubusers.app.data.UserListItem
import com.githubusers.app.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
): ViewModel(){

    private val _searchUserLiveData = MutableLiveData<List<UserListItem>>()
    val getSearchUserLiveData: LiveData<List<UserListItem>> = _searchUserLiveData

    fun searchUser(keyword: String){
        viewModelScope.launch {
            val response = searchRepository.searchUser(keyword)
            response.body()!!.items.let {
                _searchUserLiveData.postValue(it)
            }
        }
    }

    fun deleteLocalData(){
        viewModelScope.launch {
            searchRepository.deleteLocalData()
        }
    }

}