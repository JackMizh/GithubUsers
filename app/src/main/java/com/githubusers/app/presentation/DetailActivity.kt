package com.githubusers.app.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.githubusers.app.R

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val login:String = intent.getStringExtra("login").toString()


    }
}