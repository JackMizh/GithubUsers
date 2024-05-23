package com.githubusers.app.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.githubusers.app.R
import com.githubusers.app.viewmodels.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val detailViewModel: DetailViewModel by viewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val login:String = intent.getStringExtra("login").toString()
        val id:Int = intent.getIntExtra("id", 0)

        detailViewModel.getUser(id, login)

        detailViewModel.getUserLiveData.observe(this){
            it.let {
                Glide.with(this).load(it.avatar_url).into(findViewById(R.id.avatar))
                findViewById<TextView>(R.id.name_tv).text = "${it.name} • ${it.location}"
                findViewById<TextView>(R.id.email_tv).text = it.email ?: "-"
                findViewById<TextView>(R.id.bio_tv).text = it.bio ?: "-"
                findViewById<TextView>(R.id.followers_tv).text = it.followers.toString()
                findViewById<TextView>(R.id.following_tv).text = it.following.toString()
                findViewById<TextView>(R.id.repository_tv).text = it.public_repos.toString()
            }
        }
    }
}