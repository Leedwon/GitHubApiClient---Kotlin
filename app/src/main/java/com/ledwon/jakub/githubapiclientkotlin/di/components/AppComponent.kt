package com.ledwon.jakub.githubapiclientkotlin.di.components

import android.app.Application
import com.ledwon.jakub.githubapiclientkotlin.App
import com.ledwon.jakub.githubapiclientkotlin.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ActivitiesModule::class, AppModule::class, GitHubRepositoryModule::class, RemoteModule::class, ViewModelModule::class))
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application) : AppComponent.Builder

        fun build() : AppComponent
    }

    override fun inject(app: App)
}