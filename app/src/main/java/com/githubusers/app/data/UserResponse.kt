package com.githubusers.app.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class UserResponse(
    @Json(name = "avatar_url")
    val avatar_url: String,
    @Json(name = "bio")
    val bio: Any,
    @Json(name = "email")
    val email: Any,
    @Json(name = "followers")
    val followers: Int,
    @Json(name = "following")
    val following: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "location")
    val location: String,
    @Json(name = "login")
    val login: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "public_repos")
    val public_repos: Int,
)