package com.githubusers.app.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.githubusers.app.R
import com.githubusers.app.viewmodels.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val searchViewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchViewModel.searchUser("Zaki")

        searchViewModel.getSearchUserLiveData.observe(this){
            for(i in it){
                Toast.makeText(this, i.login, Toast.LENGTH_SHORT).show()
            }
        }
    }
}