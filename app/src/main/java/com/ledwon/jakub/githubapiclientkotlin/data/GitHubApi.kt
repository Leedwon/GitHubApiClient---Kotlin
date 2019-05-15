package com.ledwon.jakub.githubapiclientkotlin.data

import com.ledwon.jakub.githubapiclientkotlin.data.model.Repo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("users/{username}/repos")
    fun getListOfRepos(@Path("username")username : String) : Single<List<Repo>>

    @GET("repos/{username}/{repo}")
    fun getRepo(@Path("username") username: String, @Path("repo") repo : String) : Single<Repo>
}