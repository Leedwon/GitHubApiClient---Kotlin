package com.ledwon.jakub.githubapiclientkotlin.di.modules

import com.ledwon.jakub.githubapiclientkotlin.ui.ListOfReposActivity
import com.ledwon.jakub.githubapiclientkotlin.ui.RepoDetailsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector
    abstract fun contributeListOfReposActivity() : ListOfReposActivity

    @ContributesAndroidInjector
    abstract fun contributeRepoDetailsActivity() : RepoDetailsActivity

}