package com.githubusers.app.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.githubusers.app.data.UserListItem

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(userListItem: UserListItem): Long

    @Query("SELECT * FROM users")
    fun getAllUsers(): LiveData<List<UserListItem>>

    @Delete
    suspend fun deleteUsers(userListItem: UserListItem)
}