package com.githubusers.app.db

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): UsersDatabase {
        return UsersDatabase.invoke(context)
    }

    @Provides
    fun provideUsersDao(usersDatabase: UsersDatabase): UsersDao {
        return usersDatabase.UsersDao()
    }
}