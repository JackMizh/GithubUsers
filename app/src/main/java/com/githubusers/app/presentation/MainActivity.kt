package com.githubusers.app.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.githubusers.app.R
import com.githubusers.app.adapters.SearchAdapter
import com.githubusers.app.viewmodels.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val searchViewModel: SearchViewModel by viewModels()
    private val searchAdapter: SearchAdapter = SearchAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchViewModel.deleteLocalData()

        findViewById<RecyclerView>(R.id.users_list).apply {
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        findViewById<EditText>(R.id.search_edt).apply {
            setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (this.text.toString() != "") {
                        searchViewModel.searchUser(this.text.toString())
                    }
                    return@OnEditorActionListener true
                }
                false
            })
        }

        searchViewModel.getSearchUserLiveData.observe(this){
            it.let {
                dataToggle(it.isEmpty())
                searchAdapter.differ.submitList(it)
            }
        }

        searchAdapter.setOnItemClickListener {
            val i = Intent(this, DetailActivity::class.java)
            i.putExtra("login", it.login)
            i.putExtra("id", it.id)
            startActivity(i)
        }
    }

    private fun dataToggle(isEmpty: Boolean){
        if(isEmpty){
            findViewById<TextView>(R.id.empty_tv).visibility = View.VISIBLE
            findViewById<RecyclerView>(R.id.users_list).visibility = View.GONE
        } else {
            findViewById<TextView>(R.id.empty_tv).visibility = View.GONE
            findViewById<RecyclerView>(R.id.users_list).visibility = View.VISIBLE
        }
    }
}