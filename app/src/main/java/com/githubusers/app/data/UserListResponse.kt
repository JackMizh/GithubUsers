package com.githubusers.app.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserListResponse(
    @Json(name = "incomplete_results")
    val incomplete_results: Boolean,
    @Json(name = "items")
    val items: List<UserListItem>,
    @Json(name = "total_count")
    val total_count: Int
)