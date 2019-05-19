package com.ledwon.jakub.githubapiclientkotlin.utils

import com.google.gson.GsonBuilder
import com.ledwon.jakub.githubapiclientkotlin.data.model.Repo

class RepoJsonConverter {
    companion object {
        fun toJsonString(repo : Repo) : String = GsonBuilder().create().toJson(repo)
        fun toRepo(repo : String) : Repo = GsonBuilder().create().fromJson(repo, Repo::class.java)
    }
}