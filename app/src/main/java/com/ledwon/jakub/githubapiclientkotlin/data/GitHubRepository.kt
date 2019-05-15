package com.ledwon.jakub.githubapiclientkotlin.data

import javax.inject.Singleton

@Singleton
class GitHubRepository(private val gitHubApi: GitHubApi){

    fun getListOfRepos(username : String) = gitHubApi.getListOfRepos(username)

    fun getRepo(username : String, repoName : String) = gitHubApi.getRepo(username, repoName)
}