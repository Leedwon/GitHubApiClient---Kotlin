package com.ledwon.jakub.githubapiclientkotlin.di.modules

import com.ledwon.jakub.githubapiclientkotlin.data.GitHubApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class RemoteModule {
    companion object {
        @JvmStatic val BASE_URL = "https://api.github.com/"
    }

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideGitHubApi(retrofit: Retrofit) : GitHubApi = retrofit.create(GitHubApi::class.java)
}