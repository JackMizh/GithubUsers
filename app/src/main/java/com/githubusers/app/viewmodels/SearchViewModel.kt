package com.githubusers.app.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.githubusers.app.data.UserListItem
import com.githubusers.app.repository.SearchRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
): ViewModel(){

    private val _searchUserLiveData = MutableLiveData<UserListItem>()
    val getSearchUserLiveData: LiveData<UserListItem> = _searchUserLiveData

    fun searchUser(keyword: String){
        viewModelScope.launch {
            val response = searchRepository.searchUser(keyword)
            response.body()!!.items.let {
                _searchUserLiveData.postValue(it[0])
            }
        }
    }

}