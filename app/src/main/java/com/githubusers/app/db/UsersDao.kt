package com.githubusers.app.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.githubusers.app.data.UserResponse

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocalData(userResponse: UserResponse): Long

    @Query("SELECT * FROM users WHERE id=:id")
    suspend fun getLocalData(id: Int): UserResponse?

    @Query("DELETE FROM users")
    suspend fun deleteLocalData()
}