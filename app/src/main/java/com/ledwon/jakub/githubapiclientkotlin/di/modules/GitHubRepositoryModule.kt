package com.ledwon.jakub.githubapiclientkotlin.di.modules

import com.ledwon.jakub.githubapiclientkotlin.data.GitHubApi
import com.ledwon.jakub.githubapiclientkotlin.data.GitHubRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = arrayOf(RemoteModule::class))
class GitHubRepositoryModule {
    @Provides
    @Singleton
    fun provideGitHubRepository(gitHubApi: GitHubApi) = GitHubRepository(gitHubApi)
}