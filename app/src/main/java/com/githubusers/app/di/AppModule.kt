package com.githubusers.app.di

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.githubusers.app.api.GithubApi
import com.githubusers.app.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): GithubApi =
        Retrofit.Builder()
            .client(provideOkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(Constants.baseUrl)
            .build()
            .create(GithubApi::class.java)

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(provideChuckerInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideChuckerInterceptor(): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(MyApplication.appContext)
            .collector(ChuckerCollector(MyApplication.appContext))
            .maxContentLength(250_000L)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(true)
            .build()
    }
}