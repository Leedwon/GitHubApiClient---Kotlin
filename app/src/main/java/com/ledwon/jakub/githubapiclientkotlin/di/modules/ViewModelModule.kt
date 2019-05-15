package com.ledwon.jakub.githubapiclientkotlin.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ledwon.jakub.githubapiclientkotlin.di.ViewModelFactory
import com.ledwon.jakub.githubapiclientkotlin.di.ViewModelKey
import com.ledwon.jakub.githubapiclientkotlin.ui.viewmodels.ListOfReposViewModel
import com.ledwon.jakub.githubapiclientkotlin.ui.viewmodels.RepoDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory : ViewModelFactory) : ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ListOfReposViewModel::class)
    abstract fun bindListOfReposViewModel(viewModel : ListOfReposViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RepoDetailsViewModel::class)
    abstract fun bindRepoDetailsViewModel(viewModel : RepoDetailsViewModel) : ViewModel
}