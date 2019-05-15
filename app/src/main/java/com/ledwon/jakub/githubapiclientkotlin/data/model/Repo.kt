package com.ledwon.jakub.githubapiclientkotlin.data.model

import com.google.gson.annotations.SerializedName

data class Repo(@SerializedName("name")var name : String,
                @SerializedName("owner")var owner : RepoOwner,
                @SerializedName("fork")var fork : Boolean,
                @SerializedName("forks_count")var forksCount : Int,
                @SerializedName("watchers_count")var watchers : Int,
                @SerializedName("language")var language : String,
                @SerializedName("html_url")var htmlUrl : String,
                @SerializedName("description")var description : String)
