package com.ledwon.jakub.githubapiclientkotlin.ui

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ledwon.jakub.githubapiclientkotlin.R
import com.ledwon.jakub.githubapiclientkotlin.data.model.Repo
import com.ledwon.jakub.githubapiclientkotlin.di.ViewModelFactory
import com.ledwon.jakub.githubapiclientkotlin.ui.viewmodels.ListOfReposViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class ListOfReposActivity : AppCompatActivity() {
    companion object {
        val BUNDLE_USERNAME_KEY = "com.ledwon.jakub.githubapiclientkotlin.ui.USERNAME"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: ListOfReposViewModel

    @Inject
    lateinit var loadingFragment: LoadingFragment
    @Inject
    lateinit var listOfReposFragment: ListOfReposFragment

    @Inject
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidInjection.inject(this)

        setContentView(R.layout.activity_list_of_repos)
        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_list_of_repos_fragment_container, loadingFragment).commit()

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListOfReposViewModel::class.java)

        val username : String = intent.getStringExtra(BUNDLE_USERNAME_KEY)

        viewModel.fetchRepos(username)

        viewModel.repos.observe(
            this,
            Observer { repos: List<Repo>? -> repos?.let { listOfReposFragment.submitReposList(repos) } })

        viewModel.userFound.observe(this, Observer {
            if (it == false) {
                Toast.makeText(context, R.string.no_user, Toast.LENGTH_LONG).show()
                finish()
            }
        })

        viewModel.loading.observe(this, Observer {
            if (it == false)
                supportFragmentManager.beginTransaction()
                    .replace(R.id.activity_list_of_repos_fragment_container, listOfReposFragment).commit()

        })
    }
}